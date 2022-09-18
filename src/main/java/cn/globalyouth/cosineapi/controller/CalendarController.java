package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.calendar.CalendarDto;
import cn.globalyouth.cosineapi.service.ICalendarService;
import cn.globalyouth.cosineapi.model.vo.req.CalendarCustomReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CalendarEventReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CalendarListReqVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng
 * 日历控制器
 */
@RestController
@RequestMapping("/cosine/calendar")
public class CalendarController {

    @Resource
    private ICalendarService service;

    @PostMapping("/day")
    public ApiResponse<List<CalendarDto>> listDayEvents(@RequestBody CalendarListReqVo reqVo) {
        return service.listDayEventsV2(reqVo.getUserId(), reqVo.getDate(), reqVo.getTypes());
    }

    @PostMapping("/week")
    public ApiResponse<List<CalendarDto>> listWeekEvents(@RequestBody CalendarListReqVo reqVo) {
        return service.listWeekEventsV2(reqVo.getUserId(), reqVo.getDate(), reqVo.getTypes());
    }

    @PostMapping("/add")
    public ApiResponse<Object> addCalendar(@RequestBody CalendarEventReqVo reqVo) {
        return service.addCalendar(reqVo);
    }

    @PostMapping("/addCustom")
    public ApiResponse<Object> addCalendar(@RequestBody CalendarCustomReqVo reqVo) {
        return service.addCalendar(reqVo);
    }

    @PostMapping("/del")
    public ApiResponse<Object> delCalendar(@RequestBody CalendarEventReqVo reqVo) {
        return service.delCalendar(reqVo);
    }

    @PostMapping("/del/id")
    public ApiResponse<Object> deleteById(int id) {
        return service.delCalendar(id);
    }

    @PostMapping("/update")
    public ApiResponse<Object> updateCalendar(@RequestBody CalendarCustomReqVo reqVo) {
        return service.updateCalendar(reqVo);
    }
}
