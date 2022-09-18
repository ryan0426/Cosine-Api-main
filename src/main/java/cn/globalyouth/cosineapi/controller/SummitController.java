package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.model.bean.summit.VisaInfo;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.ISummitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyufeng, Rujun Yan
 * 峰会控制器
 */
@RestController
@RequestMapping("/summit")
public class SummitController {

    @Resource
    private ISummitService service;

    @PostMapping("/visa/add")
    public ApiResponse<Object> addInfo(@RequestBody VisaInfo data) {
        return service.addInfo(data);
    }

    @PostMapping("/visa/export")
    public ApiResponse<String> exportInfo(String dateStr) {
        return service.exportInfo(dateStr);
    }
}
