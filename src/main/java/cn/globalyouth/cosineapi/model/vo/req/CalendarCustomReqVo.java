package cn.globalyouth.cosineapi.model.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author liuyufeng
 * 日历添加请求对象 自定义事件
 */
@Data
public class CalendarCustomReqVo {

    private Integer id;

    private int user;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date fromTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date toTime;

    private int type;

    private String title;

    private int style;

    private String content;

    private int remind;

    private int zone;

    private boolean node;

    private String remark;

    private int repeat;

    private long addTime;
}
