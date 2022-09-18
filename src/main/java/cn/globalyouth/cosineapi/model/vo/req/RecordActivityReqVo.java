package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 组织活动经历
 */
@Data
public class RecordActivityReqVo {

    private Integer id;

    private int userId;

    private String activity;

    private String role;

    private String department;

    private String city;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date fromDate;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date toDate;

    private String description;

    private long addTime;
}
