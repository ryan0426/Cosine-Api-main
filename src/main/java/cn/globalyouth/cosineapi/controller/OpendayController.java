package cn.globalyouth.cosineapi.controller;

import org.springframework.web.bind.annotation.*;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IOpendayService;
import cn.globalyouth.cosineapi.model.bean.enterprise.Openday;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开放日控制器
 */
@RestController
@RequestMapping("/cosine/openday")
public class OpendayController {

    @Resource
    private IOpendayService service;

    @PostMapping("add")
    public ApiResponse<Object> addOpenday(@RequestBody Openday data) {
        return service.addOpenday(data);
    }

    @PostMapping("delete")
    public ApiResponse<Object> deleteOpenday(int id) {
        return service.deleteOpenday(id);
    }

    @GetMapping("list")
    public ApiResponse<List<Openday>> listOpenday() {
        return service.listOpenday();
    }

    @PostMapping("search")
    public ApiResponse<List<Openday>> searchOpenday(String name) {
        return service.searchOpenday(name);
    }
    
    @PostMapping("filter")
    public ApiResponse<List<Openday>> filterOpenday(String location, String month) {
        return service.filterOpenday(location,month);
    }

}