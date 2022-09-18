package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.lesson.LessonBriefDto;
import cn.globalyouth.cosineapi.service.ILessonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng
 * 课程控制器
 */
@RestController
@RequestMapping("/cosine/lesson")
public class LessonController {

    @Resource
    private ILessonService service;

    @GetMapping("/list/type")
    public ApiResponse<List<LessonBriefDto>> listByType(String typeId) {
        return service.listByType(Integer.parseInt(typeId));
    }

    @GetMapping("/list/name")
    public ApiResponse<List<LessonBriefDto>> listByName(String name) {
        return service.listByName(name);
    }

    @GetMapping("/count")
    public ApiResponse<Integer> countLessons() {
        return service.countLessons();
    }

}
