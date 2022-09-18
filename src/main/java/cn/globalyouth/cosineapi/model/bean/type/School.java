package cn.globalyouth.cosineapi.model.bean.type;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * tb_school 高等院校
 */
@Data
public class School {

    private Integer id;

    private String name;

    private String code;

    private String major;

    private String location;

    private String layer;

    private String comment;
}
