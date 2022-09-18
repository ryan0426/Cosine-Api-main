package cn.globalyouth.cosineapi.model.bean.experience;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_experience_history 面试经验搜索历史
 */
@Data
public class ExperienceHistory {

    private Integer id;

    private Integer user;

    private String word;

    private Integer type;

    private Integer count;

    private Timestamp addTime;
}
