package cn.globalyouth.cosineapi.model.bean.teachin;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_teachin 宣讲会
 */
@Data
public class TeachIn {

    private Integer id;

    private String title;

    private Timestamp time;

    private Integer industry;

    private Integer people;

    private String link;

    private String content;

    private Integer initiator;

    private String guest;

    private String image;

    private Integer isVip;

    private Timestamp addTime;
}
