package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_record_education 履历 教育经历
 */
@Data
public class RecordEducation {

    private Integer id;

    private Integer user;

    private String school;

    private String education;

    private String major;

    private String minor;

    private String college;

    private Date fromDate;

    private Date toDate;

    private String description;

    private Timestamp addTime;
}
