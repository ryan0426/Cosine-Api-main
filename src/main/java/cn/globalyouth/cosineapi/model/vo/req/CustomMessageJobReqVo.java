package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author liuyufeng
 * 定时发送自定义消息请求Vo
 */
@Data
public class CustomMessageJobReqVo {

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date time;

    private String content;

    private int userId;

    private int calendarId;

    private int remind;

    private int zone;

    private String openid;
}
