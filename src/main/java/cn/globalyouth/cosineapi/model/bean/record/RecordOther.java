package cn.globalyouth.cosineapi.model.bean.record;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_record_other 履历 其他
 */
@Data
public class RecordOther {

    private Integer id;

    private Integer user;

    private String skill;

    private String language;

    private String certificate;

    private String hobby;

    private String description;

    private Timestamp addTime;
}
