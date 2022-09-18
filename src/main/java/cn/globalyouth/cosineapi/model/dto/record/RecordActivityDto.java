package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 组织活动经历
 */
@Data
public class RecordActivityDto {

    private int id;

    private String activity;

    private String role;

    private String department;

    private String city;

    private String fromDate;

    private String toDate;

    private String description;
}
