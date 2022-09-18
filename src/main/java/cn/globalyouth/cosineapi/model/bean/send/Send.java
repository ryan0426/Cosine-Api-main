package cn.globalyouth.cosineapi.model.bean.send;

import cn.globalyouth.cosineapi.model.bean.resume.Resume;
import cn.globalyouth.cosineapi.model.bean.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_send 投递
 */
@Data
public class Send {

    private Integer id;

    private User user;

    private Resume resume;

    private String resumeTitle;

    private Company company;

    private Job job;

    private Integer state;

    //@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp addTime;

    private Integer compNum;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date1;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date2;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date3;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date4;
}
