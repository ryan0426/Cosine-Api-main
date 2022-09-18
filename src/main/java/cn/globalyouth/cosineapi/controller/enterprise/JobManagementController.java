package cn.globalyouth.cosineapi.controller.enterprise;

import cn.globalyouth.cosineapi.common.annotations.UserLoginToken;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.CloseJobBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.CreateJobBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.QueryAllBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.UpdateJobBO;
import cn.globalyouth.cosineapi.service.enterprise.EnterPriseUserService;
import cn.globalyouth.cosineapi.service.enterprise.JobManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "职位管理的controller", tags = "职位管理相关的接口")
@RequestMapping("/cosine/enterprise/job")
public class JobManagementController extends BaseController {

    @Autowired
    private JobManageService jobManageService;

    @Autowired
    private EnterPriseUserService userService;

    @PostMapping("view")
    @ApiOperation(value = "已发布职位信息", notes = "已发布职位信息", httpMethod = "POST")
    public ApiResponse view(@RequestBody @Valid @ApiParam(value = "接受参数的对象", required = true)
                                    QueryAllBO queryAllBO) {


        return ApiResponse.ok();
    }


    @PostMapping("create")
    @UserLoginToken
    @ApiOperation(value = "发布职位", notes = "发布职位", httpMethod = "POST")
    public ApiResponse create(@RequestBody @Valid @ApiParam(value = "接受参数的对象", required = true)
                                      CreateJobBO createJobBO) {

        return ApiResponse.ok();
    }

    @PostMapping("update")
    @UserLoginToken
    @ApiOperation(value = "修改职位信息", notes = "修改职位信息", httpMethod = "POST")
    public ApiResponse update(@RequestBody @Valid @ApiParam(value = "接受参数的对象", required = true)
                                      UpdateJobBO updateJobBO) {

        return ApiResponse.ok();
    }


    @PostMapping("del")
    @UserLoginToken
    @ApiOperation(value = "关闭职位", notes = "关闭职位", httpMethod = "POST")
    public ApiResponse close(@RequestBody @Valid @ApiParam(value = "接受参数的对象", required = true)
                                     CloseJobBO closeJobBO) {

        return ApiResponse.ok();
    }


    /**
     * 验证职位和当前登录的用户是否相关
     */
    private void verifyJobAndPublishUserRelated(String userId, String id) {

    }


}
