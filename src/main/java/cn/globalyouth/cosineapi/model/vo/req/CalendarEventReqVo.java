package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 日历添加请求对象
 */
@Data
public class CalendarEventReqVo {

    private Integer id;

    private int user;

    private long fromTime;

    private long toTime;

    private int type;

    private Integer event;

    private String title;

    private int style;

    private String content;

    private String remark;

    private Integer remind;

    private Integer zone;

    private boolean node;

    private long addTime;
}
