package cn.globalyouth.cosineapi.dao.calendar;

import cn.globalyouth.cosineapi.model.bean.calendar.Calendar;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_calendar 日程dao
 */
@Mapper
public interface CalendarDao {

    /**
     * 查询时间段内所有任务
     *
     * @param userId   用户id
     * @param fromTime 开始时间
     * @param toTime   结束时间
     * @param types    事件种类
     * @return List<Calendar>
     */
    @SelectProvider(type = CalendarSql.class, method = "select")
    @Results(id = "calendarMapper", value = {
            @Result(column = "from_time", property = "fromTime"),
            @Result(column = "to_time", property = "toTime"),
            @Result(column = "is_node", property = "isNode"),
            @Result(column = "repeat_type", property = "repeat")
    })
    List<Calendar> listAll(int userId, Date fromTime, Date toTime, List<Integer> types);

    /**
     * 查询时间段内所有任务
     *
     * @param userId   用户id
     * @param fromTime 开始时间
     * @param toTime   结束时间
     * @param styles   事件颜色
     * @return List<Calendar>
     */
    @SelectProvider(type = CalendarSql.class, method = "selectV2")
    @ResultMap("calendarMapper")
    List<Calendar> listAllV2(int userId, Date fromTime, Date toTime, List<Integer> styles);

    /**
     * 根据重复类型查询事件
     *
     * @param userId 用户id
     * @param date   当前日期
     * @param styles 事件颜色
     * @return List<Calendar>
     */
    @SelectProvider(type = CalendarSql.class, method = "selectRepeat")
    @ResultMap("calendarMapper")
    List<Calendar> listRepeat(int userId, Date date, List<Integer> styles);

    /**
     * 添加事件到日历
     *
     * @param data 事件数据
     * @return 0/1
     */
    @Insert("insert into tb_calendar(user,from_time,to_time,type,event,title,style,content,remark,remind,zone," +
            "is_node,repeat_type,add_time) values (#{user},#{fromTime},#{toTime},#{type},#{event},#{title},#{style}," +
            "#{content},#{remark},#{remind},#{zone},#{isNode},#{repeat},#{addTime})")
    int addCalendar(Calendar data);

    /**
     * 按添加时间查询日历事件
     *
     * @param user  用户id
     * @param title 事件标题
     * @return 事件id
     */
    @Select("select id from tb_calendar where user=#{user} and title=#{title} order by add_time desc limit 1")
    int selectLastAdded(int user, String title);

    /**
     * 统计有无相同数据
     *
     * @param data 事件数据
     * @return id
     */
    @Select("select id from tb_calendar where user=#{user} and type=#{type} and event=#{event}")
    Integer selectCalendar(Calendar data);

    /**
     * 从日历删除事件
     *
     * @param data 事件数据
     * @return 0/1
     */
    @Delete("delete from tb_calendar where user=#{user} and type=#{type} and event=#{event}")
    int delCalendar(Calendar data);

    /**
     * 按id删除事件
     *
     * @param id 事件id
     * @return 0/1
     */
    @Delete("delete from tb_calendar where id=#{id}")
    int deleteById(int id);

    /**
     * 修改日历事件数据
     *
     * @param calendar 事件数据
     * @return 0/1
     */
    @UpdateProvider(type = CalendarSql.class, method = "update")
    int updateCalendar(Calendar calendar);
}
