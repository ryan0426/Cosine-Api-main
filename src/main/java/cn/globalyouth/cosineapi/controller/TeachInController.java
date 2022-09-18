package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.TeachInDto;
import cn.globalyouth.cosineapi.service.ITeachInService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng
 * 宣讲会控制器
 */
@RestController
@RequestMapping("/cosine/teachin")
public class TeachInController {

    @Resource
    private ITeachInService service;

    @GetMapping("/list")
    public ApiResponse<List<TeachInDto>> listMockInterviews(String name, String vip) {
        return service.listMockInterviews(name, Integer.parseInt(vip));
    }
}
