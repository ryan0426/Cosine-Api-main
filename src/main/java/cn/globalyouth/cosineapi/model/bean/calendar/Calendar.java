package cn.globalyouth.cosineapi.model.bean.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_calendar 日程
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    private Integer id;

    private Integer user;

    private Timestamp fromTime;

    private Timestamp toTime;

    private Integer type;

    private Integer event;

    private String title;

    private Integer style;

    private String content;

    private String remark;

    private Integer remind;

    private Integer zone;

    private Integer isNode;

    private Integer repeat;

    private Timestamp addTime;

}



