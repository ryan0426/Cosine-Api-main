package cn.globalyouth.cosineapi.model.bean.lesson;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_lesson 课程
 */
@Data
public class Lesson {

    private Integer id;

    private String name;

    private Integer type;

    private String introduction;

    private Integer count;

    private String image;

    private Timestamp addTime;
}
