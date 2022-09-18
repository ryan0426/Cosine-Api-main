package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.HistoryDto;
import cn.globalyouth.cosineapi.service.IHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 搜索历史控制器
 */
@RestController
@RequestMapping("/cosine/history")
public class HistoryController {

    @Resource
    private IHistoryService service;

    @GetMapping("/experience/company")
    public ApiResponse<List<HistoryDto>> listExperienceHistoryCompany(String userId) {
        return service.listExperienceHistoryCompany(Integer.parseInt(userId));
    }

    @GetMapping("/experience/job")
    public ApiResponse<List<HistoryDto>> listExperienceHistoryJob(String userId) {
        return service.listExperienceHistoryJob(Integer.parseInt(userId));
    }
}
