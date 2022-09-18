package cn.globalyouth.cosineapi.model.dto.lesson;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 课程简要信息
 */
@Data
public class LessonBriefDto {

    private int id;

    private String name;

    private String introduction;

    private int count;

    private String image;
}
