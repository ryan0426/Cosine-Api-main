package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 教育经历
 */
@Data
public class RecordEducationDto {

    private int id;

    private String school;

    private String education;

    private String major;

    private String minor;

    private String college;

    private String fromDate;

    private String toDate;

    private String description;
}
