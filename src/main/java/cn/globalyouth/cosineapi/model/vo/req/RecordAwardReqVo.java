package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 获奖经历
 */
@Data
public class RecordAwardReqVo {

    private Integer id;

    private int userId;

    private String award;

    private String level;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date date;

    private String description;

    private long addTime;
}
