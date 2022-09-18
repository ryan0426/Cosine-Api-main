package cn.globalyouth.cosineapi.model.bean.ad;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_banner 广告图
 */
@Data
public class Banner {

    private Integer id;

    private Integer position;

    private String image;

    private String link;

    private Timestamp addTime;
}
