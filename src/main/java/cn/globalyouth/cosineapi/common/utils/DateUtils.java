package cn.globalyouth.cosineapi.common.utils;

import cn.globalyouth.cosineapi.model.bean.calendar.Calendar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 时间处理工具类
 */
public class DateUtils {

    public static Calendar getDailyEvent(Calendar calendar, Date date) {
        updateDate(date, calendar);
        return calendar;
    }

    public static Calendar getWeeklyEvent(Calendar calendar, Date date) {
        Timestamp fromTime = calendar.getFromTime();
        Timestamp toTime = calendar.getToTime();
        if (fromTime.getDay() == date.getDay()) {
            fromTime.setYear(date.getYear());
            fromTime.setMonth(date.getMonth());
            fromTime.setDate(date.getDate());
            toTime.setYear(date.getYear());
            toTime.setMonth(date.getMonth());
            toTime.setDate(date.getDate());
            return calendar;
        }
        return null;
    }

    public static Calendar getMonthlyEvent(Calendar calendar, Date date) {
        Timestamp fromTime = calendar.getFromTime();
        Timestamp toTime = calendar.getToTime();
        if (fromTime.getDate() == date.getDate()) {
            fromTime.setYear(date.getYear());
            fromTime.setMonth(date.getMonth());
            fromTime.setDate(date.getDate());
            toTime.setYear(date.getYear());
            toTime.setMonth(date.getMonth());
            toTime.setDate(date.getDate());
            return calendar;
        }
        return null;
    }

    public static List<Calendar> getDailyEvents(Calendar calendar, Date date) {
        List<Calendar> res = new ArrayList<>();
        java.util.Calendar cd = java.util.Calendar.getInstance();
        cd.setTime(date);
        cd.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY);
        Date day = cd.getTime();
        for (int i = 0; i < 7; i++) {
            if (day.getTime() >= calendar.getFromTime().getTime()) {
                Calendar c = new Calendar(calendar.getId(), calendar.getUser(), new Timestamp(calendar.getFromTime().getTime()),
                        new Timestamp(calendar.getToTime().getTime()), calendar.getType(), calendar.getEvent(),
                        calendar.getTitle(), calendar.getStyle(), calendar.getContent(), calendar.getRemark(),
                        calendar.getRemind(), calendar.getZone(), calendar.getIsNode(), calendar.getRepeat(), calendar.getAddTime());
                updateDate(day, c);
                res.add(c);
            }
            day.setTime(day.getTime() + 24 * 60 * 60 * 1000);
        }
        return res;
    }

    public static Calendar getWeeklyEvents(Calendar calendar, Date date) {
        java.util.Calendar cd = java.util.Calendar.getInstance();
        cd.setTime(date);
        cd.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY);
        Date day = cd.getTime();
        for (int i = 0; i < 7; i++) {
            if (day.getTime() >= calendar.getFromTime().getTime() && day.getDay() == calendar.getFromTime().getDay()) {
                updateDate(day, calendar);
                return calendar;
            }
            day.setTime(day.getTime() + 24 * 60 * 60 * 1000);
        }
        return null;
    }

    public static Calendar getMonthlyEvents(Calendar calendar, Date date) {
        java.util.Calendar cd = java.util.Calendar.getInstance();
        cd.setTime(date);
        cd.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY);
        Date day = cd.getTime();
        for (int i = 0; i < 7; i++) {
            if (day.getTime() >= calendar.getFromTime().getTime() && day.getDate() == calendar.getFromTime().getDate()) {
                updateDate(day, calendar);
                return calendar;
            }
            day.setTime(day.getTime() + 24 * 60 * 60 * 1000);
        }
        return null;
    }

    private static void updateDate(Date day, Calendar c) {
        Timestamp fromTime = c.getFromTime();
        Timestamp toTime = c.getToTime();
        fromTime.setYear(day.getYear());
        fromTime.setMonth(day.getMonth());
        fromTime.setDate(day.getDate());
        toTime.setYear(day.getYear());
        toTime.setMonth(day.getMonth());
        toTime.setDate(day.getDate());
    }

}
