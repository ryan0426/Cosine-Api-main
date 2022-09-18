package cn.globalyouth.cosineapi.model.bean.enterprise;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.util.Date;

/**
 * tb_enterprise_openday 开放日
 */
@Data
public class Openday {

    private Integer id;

    private String image;

    private String name;

    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM",timezone = "GMT+8")
    private Date month;

    private String day;

    private String startTime;

    private String endTime;

    private String location;

    private String survey_url;
}