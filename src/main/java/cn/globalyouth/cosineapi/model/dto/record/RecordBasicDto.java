package cn.globalyouth.cosineapi.model.dto.record;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 基本信息
 */
@Data
public class RecordBasicDto {

    private int id;

    private String name;

    private String gender;

    private String phone;

    private String email;

    private String location;

    private String other;
}
