package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.calendar.CalendarDto;
import cn.globalyouth.cosineapi.model.vo.req.CalendarCustomReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CalendarEventReqVo;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 日程服务
 */
public interface ICalendarService {

    /**
     * 添加事件到日历
     *
     * @param reqVo 事件请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addCalendar(CalendarEventReqVo reqVo);

    /**
     * 添加事件到日历
     *
     * @param reqVo 事件请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addCalendar(CalendarCustomReqVo reqVo);

    /**
     * 从日历删除日程
     *
     * @param reqVo 事件数据
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> delCalendar(CalendarEventReqVo reqVo);

    /**
     * 按id删除事件
     *
     * @param id 事件id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> delCalendar(int id);

    /**
     * 修改日历事件数据
     *
     * @param reqVo 事件数据
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateCalendar(CalendarCustomReqVo reqVo);

    /**
     * 列出当日日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param types  事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    ApiResponse<List<CalendarDto>> listDayEvents(int userId, String date, List<Integer> types);

    /**
     * 列出当日日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param styles 事件颜色
     * @return ApiResponse<List < CalendarDto>>
     */
    ApiResponse<List<CalendarDto>> listDayEventsV2(int userId, String date, List<Integer> styles);

    /**
     * 列出本周日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param types  事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    ApiResponse<List<CalendarDto>> listWeekEvents(int userId, String date, List<Integer> types);

    /**
     * 列出本周日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param styles 事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    ApiResponse<List<CalendarDto>> listWeekEventsV2(int userId, String date, List<Integer> styles);
}
