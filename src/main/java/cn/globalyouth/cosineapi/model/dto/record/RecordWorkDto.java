package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

/**
 * @author liuyufeng
 * 履历 工作经历
 */
@Data
public class RecordWorkDto {

    private int id;

    private String company;

    private String position;

    private String department;

    private String city;

    private String fromDate;

    private String toDate;

    private String description;
}
