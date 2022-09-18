package cn.globalyouth.cosineapi.controller;

import org.springframework.web.bind.annotation.*;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IOrientationService;
import cn.globalyouth.cosineapi.model.bean.enterprise.Orientation;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宣讲会控制器
 */
@RestController
@RequestMapping("/cosine/orientation")
public class OrientationController {

    @Resource
    private IOrientationService service;

    @PostMapping("add")
    public ApiResponse<Object> addOrientation(@RequestBody Orientation data) {
        return service.addOrientation(data);
    }

    @PostMapping("delete")
    public ApiResponse<Object> deleteOrientation(int id) {
        return service.deleteOrientation(id);
    }

    @GetMapping("list")
    public ApiResponse<List<Orientation>> listOrientation() {
        return service.listOrientation();
    }

    @PostMapping("search")
    public ApiResponse<List<Orientation>> searchOrientation(String name) {
        return service.searchOrientation(name);
    }
    

}