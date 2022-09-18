package cn.globalyouth.cosineapi.dao.calendar;

import cn.globalyouth.cosineapi.common.enums.EventTypeEnum;
import cn.globalyouth.cosineapi.model.bean.calendar.Calendar;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;

/**
 * @author liuyufeng
 * 日历sql语句生成
 */
public class CalendarSql {

    public String select(int userId, Date fromTime, Date toTime, List<Integer> types) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tb_calendar");
        sql.WHERE("user=#{userId}")
                .WHERE("from_time>#{fromTime}")
                .WHERE("to_time<#{toTime}");
        if (null != types && !types.isEmpty()) {
            StringBuilder condition = new StringBuilder("type=" + types.get(0));
            for (int i = 1; i < types.size(); i++) {
                condition.append(" OR type=").append(types.get(i));
            }
            sql.AND().WHERE(condition.toString());
        }
        return sql.toString();
    }

    public String selectV2(int userId, Date fromTime, Date toTime, List<Integer> styles) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tb_calendar");
        sql.WHERE("user=#{userId}")
                .WHERE("from_time>#{fromTime}")
                .WHERE("to_time<#{toTime}");
        return getString(styles, sql);
    }

    public String selectRepeat(int userId, Date date, List<Integer> styles) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tb_calendar").WHERE("user=#{userId}").WHERE("from_time<#{date}")
                .WHERE("repeat_type in (1,2,3,4)");
        return getString(styles, sql);
    }

    private String getString(List<Integer> styles, SQL sql) {
        if (null != styles && !styles.isEmpty()) {
            StringBuilder condition = new StringBuilder("style=" + styles.get(0));
            for (int i = 1; i < styles.size(); i++) {
                condition.append(" OR style=").append(styles.get(i));
            }
            sql.AND().WHERE(condition.toString());
        }
        return sql.toString();
    }

    public String update(Calendar calendar) {
        SQL sql = new SQL();
        sql.UPDATE("tb_calendar");
        if (calendar.getType() == EventTypeEnum.CUSTOM.getCode() || calendar.getType() == EventTypeEnum.NODE.getCode()) {
            sql.SET("from_time=#{fromTime}", "to_time=#{toTime}", "type=#{type}", "title=#{title}", "style=#{style}",
                    "content=#{content}", "repeat_type=#{repeat}", "add_time=#{addTime}");
        } else {
            sql.SET("remark=#{remark}");
        }
        sql.SET("remind=#{remind}", "zone=#{zone}");
        if (null == calendar.getId()) {
            sql.WHERE("1=2");
        } else {
            sql.WHERE("id=#{id}");
        }
        return sql.toString();
    }
}
