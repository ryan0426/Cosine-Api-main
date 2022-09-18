package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 其他
 */
@Data
public class RecordOtherReqVo {

    private Integer id;

    private int userId;

    private String skill;

    private String language;

    private String certificate;

    private String hobby;

    private String description;

    private long addTime;
}
