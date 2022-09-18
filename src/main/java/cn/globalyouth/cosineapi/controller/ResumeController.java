package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.annotations.NeedToken;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseBriefDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeImportDto;
import cn.globalyouth.cosineapi.model.pojo.TbResume;
import cn.globalyouth.cosineapi.service.IResumeService;
import cn.globalyouth.cosineapi.model.vo.req.ResumeReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 简历控制器
 */
@RestController
@RequestMapping("/cosine/resume")
@Api(value = "简历相关的接口", tags = "简历相关的接口")
@Validated
public class ResumeController {

    @Resource
    private IResumeService service;

    @NeedToken
    @GetMapping("/list")
    @ApiOperation(value = "响应参数有变化")
    //响应参数有变化
    public ApiResponse listResume(@RequestParam @NotNull(message = "用户ID不能为空")
                                          @ApiParam(example = "20") Integer userId) {
        List<TbResume> tbResumes = service.queryByUserIdList(userId);
        return ApiResponse.ok(tbResumes);
    }

    @GetMapping("/top")
    @ApiOperation(value = "响应参数有变化")
    //响应参数有变化
    public ApiResponse selectTopResume(@RequestParam @NotNull(message = "用户ID不能为空")
                                           @ApiParam(example = "20") Integer userId) {
        TbResume tbResume = service.selectTopResume(userId);
        return ApiResponse.ok(tbResume);
    }

    @NeedToken
    @GetMapping("/id")
    @ApiOperation(value = "响应参数有变化")
    //响应参数有变化
    public ApiResponse selectResume(@RequestParam @NotNull(message = "ID不能为空")
                                        @ApiParam(example = "32") Integer id) {
        TbResume tbResume = service.selectResume(id);
        return ApiResponse.ok(tbResume);
    }

    @NeedToken
    @GetMapping("/count")
    //能用
    public ApiResponse<Integer> countResume(String userId) {
        return service.countResume(Integer.parseInt(userId));
    }


    @NeedToken
    @PostMapping("/default")
    //能用
    public ApiResponse<Object> setDefaultResume(String userId, String id) {
        return service.setDefaultResume(Integer.parseInt(userId), Integer.parseInt(id));
    }

    @PostMapping("/title")
    @NeedToken
    //能用
    public ApiResponse<Object> updateTitle(String id, String title) {
        return service.updateTitle(Integer.parseInt(id), title);
    }

    @PostMapping("/import")
    public ApiResponse<ResumeImportDto> importResume(MultipartFile file) {
        return service.importResume(file);
    }

    @PostMapping("/add")
    public ApiResponse<Object> addResume(@RequestBody ResumeReqVo reqVo) {
        return service.addResume(reqVo);
    }


    @PostMapping("/delete")
    @NeedToken
    //能用
    public ApiResponse<Object> deleteResume(String id) {
        return service.deleteResume(Integer.parseInt(id));
    }

    @GetMapping("/case/hot")
    //无变化
    public ApiResponse<List<ResumeCaseBriefDto>> listHot() {
        return service.listHot();
    }

    @GetMapping("/case/list")
    //无变化
    public ApiResponse<List<ResumeCaseBriefDto>> listByIndustry(String industryId) {
        return service.listByIndustry(Integer.parseInt(industryId));
    }

    @GetMapping("/case")
    //无变化
    public ApiResponse<ResumeCaseDto> selectById(String id) {
        return service.selectById(Integer.parseInt(id));
    }
}
