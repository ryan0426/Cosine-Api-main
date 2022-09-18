package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_basic 履历 基本信息
 */
@Data
public class RecordBasic {

    private Integer id;

    private Integer user;

    private String name;

    private Integer gender;

    private String phone;

    private String email;

    private String location;

    private String other;

    private Timestamp addTime;
}
