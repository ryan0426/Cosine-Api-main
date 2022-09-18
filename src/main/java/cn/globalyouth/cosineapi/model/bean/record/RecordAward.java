package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_award 履历 获奖经历
 */
@Data
public class RecordAward {

    private Integer id;

    private Integer user;

    private String award;

    private String level;

    private Date date;

    private String description;

    private Timestamp addTime;
}
