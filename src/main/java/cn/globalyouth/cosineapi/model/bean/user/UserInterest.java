package cn.globalyouth.cosineapi.model.bean.user;

import cn.globalyouth.cosineapi.model.bean.send.Job;
import lombok.Data;

import java.sql.Timestamp;

/**
 * tb_user_intention 关注列表
 */
@Data
public class UserInterest {

    private Integer user_id;

    private Integer job_id;

    private Job job;

    private Timestamp subscribe_time;

}