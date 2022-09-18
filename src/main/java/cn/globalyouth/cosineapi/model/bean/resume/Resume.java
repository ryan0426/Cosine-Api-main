package cn.globalyouth.cosineapi.model.bean.resume;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_resume 简历
 */
@Data
public class Resume {

    private Integer id;

    private Integer user;

    private String title;

    private String recordValues;

    private String resumeKey;

    private Integer isDefault;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy/MM/dd",timezone="GMT+8")
    private Timestamp addTime;
}
