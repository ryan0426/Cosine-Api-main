package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IResumeService;

import org.springframework.web.bind.annotation.*;
import cn.globalyouth.cosineapi.model.dto.resume.VideoResumeDto;
import java.util.List;
import javax.annotation.Resource;

/**
 * 视频简历控制器
 */
@RestController
@RequestMapping("/cosine/videoresume")
public class VideoResumeController {

    @Resource
    private IResumeService service;

    
    @PostMapping("/add")
    public ApiResponse<Object> addVideoResume(int userId, String key) {
        return service.addVideoResume(userId, key);
    }

    @PostMapping("/delete")
    public ApiResponse<Object> deleteVideoResume(int id) {
        return service.deleteVideoResume(id);
    }

    @GetMapping("/id")
    public ApiResponse<List<VideoResumeDto>> selectVideoResume(int id) {
        return service.selectVideoResume(id);
    }
    
}