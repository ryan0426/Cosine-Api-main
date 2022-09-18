package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.lesson.Lesson;
import cn.globalyouth.cosineapi.dao.lesson.LessonDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.lesson.LessonBriefDto;
import cn.globalyouth.cosineapi.service.ILessonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;


import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * @author liuyufeng
 * 课程服务
 */
@Service
@Slf4j
public class LessonServiceImpl implements ILessonService {

    @Resource
    private LessonDao lessonDao;

    @Override
    public ApiResponse<List<LessonBriefDto>> listByType(int typeId) {
        try {
            List<Lesson> list = lessonDao.listByType(typeId);
            List<LessonBriefDto> data = new ArrayList<>();
            list.forEach(lesson -> data.add(handleBriefLesson(lesson)));
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() +"\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<LessonBriefDto>> listByName(String name) {
        try {
            List<Lesson> list = lessonDao.listByName("%" + name + "%");
            List<LessonBriefDto> data = new ArrayList<>();
            list.forEach(lesson -> data.add(handleBriefLesson(lesson)));
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() +"\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Integer> countLessons() {
        try {
            return ApiResponse.success(lessonDao.countLessons());
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() +"\r\n\r\n");
            return ApiResponse.error();
        }
    }

    private LessonBriefDto handleBriefLesson(Lesson lesson) {
        LessonBriefDto res = new LessonBriefDto();
        res.setId(lesson.getId());
        res.setName(lesson.getName());
        res.setIntroduction(lesson.getIntroduction());
        res.setImage(lesson.getImage());
        res.setCount(lesson.getCount());
        return res;
    }
}
