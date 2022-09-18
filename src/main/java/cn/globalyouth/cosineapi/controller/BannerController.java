package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.BannerDto;
import cn.globalyouth.cosineapi.service.IBannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 广告图片控制器
 */
@RestController
@RequestMapping("/cosine/banner")
public class BannerController {

    @Resource
    private IBannerService service;

    @GetMapping("/list")
    public ApiResponse<List<BannerDto>> listBanners(String position) {
        return service.listBanners(Integer.parseInt(position));
    }
    

    
}
