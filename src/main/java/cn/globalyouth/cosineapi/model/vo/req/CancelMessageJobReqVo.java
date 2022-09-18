package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng
 * 取消订阅消息请求
 */
@Data
public class CancelMessageJobReqVo {

    private int userId;

    private int calendarId;

    private String typeChar;
}
