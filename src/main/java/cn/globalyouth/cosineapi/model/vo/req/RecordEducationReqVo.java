package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 教育经历
 */
@Data
public class RecordEducationReqVo {

    private Integer id;

    private int userId;

    private String school;

    private String education;

    private String major;

    private String minor;

    private String college;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date fromDate;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date toDate;

    private String description;

    private long addTime;
}
