package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.lesson.LessonBriefDto;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 课程服务
 */
public interface ILessonService {

    /**
     * 根据类型查询课程
     *
     * @param typeId 类型id
     * @return ApiResponse<List < LessonBriefDto>>
     */
    ApiResponse<List<LessonBriefDto>> listByType(int typeId);

    /**
     * 根据名称查询课程
     *
     * @param name 名称
     * @return ApiResponse<List < LessonBriefDto>>
     */
    ApiResponse<List<LessonBriefDto>> listByName(String name);

    /**
     * 统计课程总数
     *
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> countLessons();
}
