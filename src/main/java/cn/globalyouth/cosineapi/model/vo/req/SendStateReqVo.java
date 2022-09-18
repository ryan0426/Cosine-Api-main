package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 投递进度更新
 */
@Data
public class SendStateReqVo {

    private Integer userId;

    private Integer jobId;

    private Integer state;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date2;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date3;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd",timezone = "GMT+8")
    private Date date4;
}