package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_profile 履历 头像
 */
@Data
public class RecordProfile {

    private Integer id;

    private Integer user;

    private String profile;

    private Timestamp addTime;
}
