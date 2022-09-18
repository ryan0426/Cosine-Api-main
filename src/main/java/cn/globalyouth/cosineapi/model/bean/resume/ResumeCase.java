package cn.globalyouth.cosineapi.model.bean.resume;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_resume_case 简历案例
 */
@Data
public class ResumeCase {

    private Integer id;

    private Integer industry;

    private String title;

    private String introduction;

    private String image;

    private String tags;

    private Integer view;

    private Timestamp addTime;
}
