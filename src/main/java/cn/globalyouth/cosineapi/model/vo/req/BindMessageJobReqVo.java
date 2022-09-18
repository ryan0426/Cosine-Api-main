package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 定时发送预置消息请求Vo
 */
@Data
public class BindMessageJobReqVo {

    private String title;

    private long time;

    private String way;

    private String link;

    private String remark;

    private int userId;

    private int calendarId;

    private int remind;

    private int zone;

    private String openid;
}
