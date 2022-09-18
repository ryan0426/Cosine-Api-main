package cn.globalyouth.cosineapi.model.bean.send;

import cn.globalyouth.cosineapi.model.bean.user.User;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_job_view 查看职位
 */
@Data
public class JobView {

    private Integer id;

    private User user;

    private Company company;

    private Job job;

    private Timestamp addTime;
}
