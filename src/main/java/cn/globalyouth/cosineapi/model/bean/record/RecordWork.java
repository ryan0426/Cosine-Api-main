package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_work 履历 工作经历
 */
@Data
public class RecordWork {

    private Integer id;

    private Integer user;

    private String company;

    private String position;

    private String department;

    private String city;

    private Date fromDate;

    private Date toDate;

    private String description;

    private Timestamp addTime;
}
