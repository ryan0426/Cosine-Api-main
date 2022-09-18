package cn.globalyouth.cosineapi.controller.enterprise;

import cn.globalyouth.cosineapi.common.annotations.UserLoginToken;
import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.bo.enterprise.user.UserLoginForEmailBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.user.UserLoginForUsernameBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.user.UserRegisterBO;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInfo;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseUser;
import cn.globalyouth.cosineapi.model.vo.enterprise.UserVO;
import cn.globalyouth.cosineapi.service.IUploadService;
import cn.globalyouth.cosineapi.service.enterprise.EnterPriseInfoService;
import cn.globalyouth.cosineapi.service.enterprise.EnterPriseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 企业用户通行的controller
 */
@RestController
@Api(value = "企业用户", tags = "企业用户相关的接口")
@RequestMapping("/cosine/enterprise")
public class PassPortController extends BaseController {
    @Autowired
    private EnterPriseUserService enterPriseUserService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private EnterPriseInfoService enterPriseInfoService;

    /**
     * 企业用户注册的接口
     *
     * @param userRegisterBO 接受参数的bo对象
     * @return
     */
    @PostMapping("register/hr")
    @ApiOperation(value = "企业用户注册", notes = "企业用户注册", httpMethod = "POST")
    public ApiResponse register(@RequestBody @Valid @ApiParam
            (value = "接受用户注册参数", required = true) UserRegisterBO userRegisterBO) {
        TbEnterpriseUser userForUsername = enterPriseUserService.queryByUsername(userRegisterBO.getUsername());
        TbEnterpriseUser userForEmail = enterPriseUserService.queryByEmail(userRegisterBO.getEmail());
        if(userForEmail != null || userForUsername != null){
            return ApiResponse.errorCustom(ResponseEnumState.THE_USERNAME_OR_EMAIL_ALREADY_EXISTS);
        }
        TbEnterpriseInfo enterpriseInfo = enterPriseInfoService.queryByName(userRegisterBO.getName());
        if(enterpriseInfo != null){
            String enterpriseId = enterpriseInfo.getId();
            enterPriseUserService.createUser(userRegisterBO,enterpriseId);
        }else{
            UserVO userVO = enterPriseUserService.createUser(userRegisterBO);
        }
        return ApiResponse.ok();
    }


    @PostMapping("register/upload_cert")
    @UserLoginToken
    @ApiOperation(value = "企业用户上传图片", notes = "企业用户上传图片", httpMethod = "POST")
    public ApiResponse upload(String type, String suffix, long length) {
        // TODO: 2021/7/4   1.0版本上线测试后 可能要扩展一下  扩展一个营业执照文件夹
        return uploadService.upload_oss(type, suffix, length);
    }


    @PostMapping("login/username")
    @ApiOperation(value = "企业用户用户名登录", notes = "企业用户用户名登录", httpMethod = "POST")
    public ApiResponse loginForUsername(@RequestBody @Valid @ApiParam(value = "接受用户登录参数", required = true)
                                                UserLoginForUsernameBO userLoginForUsernameBO, @ApiIgnore HttpSession session,
                                        HttpServletResponse response, HttpServletRequest request) {
        String username = userLoginForUsernameBO.getUsername();
        String password = userLoginForUsernameBO.getPassword();
        TbEnterpriseUser user = enterPriseUserService.queryByUsername(username);
        if(user == null){
            return ApiResponse.errorCustom(ResponseEnumState.WRONG_USER_NAME_OR_PASSWORD);
        }
        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        UserVO userVO = null;
        if(checkpw){
            TbEnterpriseInfo enterpriseInfo = enterPriseInfoService.queryById(user.getEnterpriseId());
            userVO = setterUserVO(user, enterpriseInfo);
            setHeaderToken(response,user.getId());
        }else {
            return ApiResponse.errorCustom(ResponseEnumState.WRONG_USER_NAME_OR_PASSWORD);
        }
        return ApiResponse.ok(userVO);
    }



    @PostMapping("login/email")
    @ApiOperation(value = "企业用户邮箱登录", notes = "企业用户邮箱登录", httpMethod = "POST")
    public ApiResponse loginForEmail(@RequestBody @Valid @ApiParam(value = "接受用户登录参数", required = true)
                                             UserLoginForEmailBO userLoginForEmailBO,
                                     HttpServletResponse response,
                                     HttpServletRequest request){
        String email = userLoginForEmailBO.getEmail();
        String password = userLoginForEmailBO.getPassword();
        TbEnterpriseUser user = enterPriseUserService.queryByEmail(email);
        if(user == null){
            return ApiResponse.errorCustom(ResponseEnumState.WRONG_USER_NAME_OR_PASSWORD);
        }
        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        UserVO userVO = null;
        if(checkpw){
            TbEnterpriseInfo enterpriseInfo = enterPriseInfoService.queryById(user.getEnterpriseId());
            userVO = setterUserVO(user, enterpriseInfo);
            setHeaderToken(response,user.getId());
        }else {
            return ApiResponse.errorCustom(ResponseEnumState.WRONG_USER_NAME_OR_PASSWORD);
        }
        return ApiResponse.ok(userVO);


    }


    /**
     * 设置userVO
     * @return
     */
    private UserVO setterUserVO(TbEnterpriseUser user,TbEnterpriseInfo info){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        BeanUtils.copyProperties(info,userVO);
        userVO.setCompanyId(user.getEnterpriseId());
        return userVO;
    }



}
