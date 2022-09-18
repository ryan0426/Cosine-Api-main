package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 获奖经历
 */
@Data
public class RecordAwardDto {

    private Integer id;

    private String award;

    private String level;

    private String date;

    private String description;
}
