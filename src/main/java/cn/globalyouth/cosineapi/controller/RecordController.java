package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.record.*;
import cn.globalyouth.cosineapi.service.IRecordService;
import cn.globalyouth.cosineapi.model.vo.req.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author liuyufeng, Rujun Yan
 * 履历库控制器
 */
@RestController
@RequestMapping("/cosine/record")
public class RecordController {

    @Resource
    private IRecordService service;

    @PostMapping("/profile/upload")
    public ApiResponse<String> uploadProfile(MultipartFile profile) {
        return service.uploadProfile(profile);
    }

    @PostMapping("/profile/add")
    public ApiResponse<Object> addProfile(@RequestBody RecordProfileReqVo reqVo) {
        return service.addProfile(reqVo);
    }

    @PostMapping("/basic/add")
    public ApiResponse<Object> addBasic(@RequestBody RecordBasicReqVo reqVo) {
        return service.addBasic(reqVo);
    }

    @PostMapping("/basic/update")
    public ApiResponse<Object> updateBasic(@RequestBody RecordBasicReqVo reqVo) {
        return service.updateBasic(reqVo);
    }

    @GetMapping("/basic")
    public ApiResponse<RecordBasicDto> selectBasic(String id) {
        return service.selectBasic(Integer.parseInt(id));
    }

    @PostMapping("/work/add")
    public ApiResponse<Object> addWork(@RequestBody RecordWorkReqVo reqVo) {
        return service.addWork(reqVo);
    }

    @PostMapping("/work/del")
    public ApiResponse<Object> deleteWork(String id) {
        return service.deleteWork(Integer.parseInt(id));
    }

    @PostMapping("/work/update")
    public ApiResponse<Object> updateWork(@RequestBody RecordWorkReqVo reqVo) {
        return service.updateWork(reqVo);
    }

    @GetMapping("/work")
    public ApiResponse<RecordWorkDto> selectWork(String id) {
        return service.selectWork(Integer.parseInt(id));
    }

    @PostMapping("/activity/add")
    public ApiResponse<Object> addActivity(@RequestBody RecordActivityReqVo reqVo) {
        return service.addActivity(reqVo);
    }

    @PostMapping("/activity/del")
    public ApiResponse<Object> deleteActivity(String id) {
        return service.deleteActivity(Integer.parseInt(id));
    }

    @PostMapping("/activity/update")
    public ApiResponse<Object> updateActivity(@RequestBody RecordActivityReqVo reqVo) {
        return service.updateActivity(reqVo);
    }

    @GetMapping("/activity")
    public ApiResponse<RecordActivityDto> selectActivity(String id) {
        return service.selectActivity(Integer.parseInt(id));
    }

    @PostMapping("/education/add")
    public ApiResponse<Object> addEducation(@RequestBody RecordEducationReqVo reqVo) {
        return service.addEducation(reqVo);
    }

    @PostMapping("/education/update")
    public ApiResponse<Object> updateEducation(@RequestBody RecordEducationReqVo reqVo) {
        return service.updateEducation(reqVo);
    }

    @PostMapping("/education/del")
    public ApiResponse<Object> deleteEducation(String id) {
        return service.deleteEducation(Integer.parseInt(id));
    }

    @GetMapping("/education")
    public ApiResponse<RecordEducationDto> selectEducation(String id) {
        return service.selectEducation(Integer.parseInt(id));
    }

    @PostMapping("/award/add")
    public ApiResponse<Object> addAward(@RequestBody RecordAwardReqVo reqVo) {
        return service.addAward(reqVo);
    }

    @PostMapping("/award/update")
    public ApiResponse<Object> updateAward(@RequestBody RecordAwardReqVo reqVo) {
        return service.updateAward(reqVo);
    }

    @PostMapping("/award/del")
    public ApiResponse<Object> deleteAward(String id) {
        return service.deleteAward(Integer.parseInt(id));
    }

    @GetMapping("/award")
    public ApiResponse<RecordAwardDto> selectAward(String id) {
        return service.selectAward(Integer.parseInt(id));
    }

    @PostMapping("/other/add")
    public ApiResponse<Object> addOther(@RequestBody RecordOtherReqVo reqVo) {
        return service.addOther(reqVo);
    }

    @PostMapping("/other/del")
    public ApiResponse<Object> deleteOther(String id) {
        return service.deleteOther(Integer.parseInt(id));
    }

    @PostMapping("/other/update")
    public ApiResponse<Object> updateOther(@RequestBody RecordOtherReqVo reqVo) {
        return service.updateOther(reqVo);
    }

    @GetMapping("/other")
    public ApiResponse<RecordOtherDto> selectOther(String id) {
        return service.selectOther(Integer.parseInt(id));
    }

    @GetMapping("/brief")
    public ApiResponse<RecordDto> selectRecord(String userId) {
        return service.selectRecord(Integer.parseInt(userId));
    }
}
