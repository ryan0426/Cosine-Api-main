package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng
 * 履历 基本信息
 */
@Data
public class RecordBasicReqVo {

    private Integer id;

    private int userId;

    private String name;

    private Integer gender;

    private String phone;

    private String email;

    private String location;

    private String other;

    private long addTime;
}
