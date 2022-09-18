package cn.globalyouth.cosineapi.model.bean.enterprise;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.util.Date;

/**
 * tb_enterprise_orientation 开放日
 */
@Data
public class Orientation {

    private Integer id;

    private String image;

    private String name;

    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private String startTime;

    private String endTime;

}