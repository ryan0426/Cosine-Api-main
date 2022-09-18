package cn.globalyouth.cosineapi.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * 进度dto
 */
@Data
public class SendStateDto {

    private Integer id;

    private Integer jobId;

    private Integer state;

    private String title;

    private String comName;

    private String comLogo;

    private Timestamp addTime;

    private Integer compNum;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date1;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date2;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date3;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date4;
    
}