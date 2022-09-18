package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_record_activity 履历 组织活动经历
 */
@Data
public class RecordActivity {

    private Integer id;

    private Integer user;

    private String activity;

    private String role;

    private String department;

    private String city;

    private Date fromDate;

    private Date toDate;

    private String description;

    private Timestamp addTime;
}
