package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author liuyufeng
 * 履历 工作经历
 */
@Data
public class RecordWorkReqVo {

    private Integer id;

    private int userId;

    private String company;

    private String position;

    private String department;

    private String city;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date fromDate;

    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date toDate;

    private String description;

    private long addTime;
}
