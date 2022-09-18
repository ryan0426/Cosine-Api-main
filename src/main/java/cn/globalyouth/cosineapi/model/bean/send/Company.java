package cn.globalyouth.cosineapi.model.bean.send;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_company 公司
 */
@Data
public class Company {

    private Integer id;

    private String name;

    private String logo;

    private String nature;

    private String industry;

    private String website;

    private String address;

    private String description;

    private String welfare;

    private String contact;

    private String email;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addTime;

    private String slogan;
}
