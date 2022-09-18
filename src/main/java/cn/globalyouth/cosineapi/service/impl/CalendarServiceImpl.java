package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.enums.EventTypeEnum;
import cn.globalyouth.cosineapi.common.enums.RepeatEnum;
import cn.globalyouth.cosineapi.common.enums.StyleEnum;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.bean.calendar.Calendar;
import cn.globalyouth.cosineapi.model.bean.mock.JoinMock;
import cn.globalyouth.cosineapi.model.bean.mock.MockInterview;
import cn.globalyouth.cosineapi.model.bean.teachin.TeachIn;
import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.dao.calendar.CalendarDao;
import cn.globalyouth.cosineapi.dao.mock.JoinMockDao;
import cn.globalyouth.cosineapi.dao.mock.MockInterviewDao;
import cn.globalyouth.cosineapi.dao.teachin.TeachInDao;
import cn.globalyouth.cosineapi.dao.type.IndustryDao;
import cn.globalyouth.cosineapi.dao.user.UserDao;
import cn.globalyouth.cosineapi.model.dto.*;
import cn.globalyouth.cosineapi.model.dto.calendar.CalendarDto;
import cn.globalyouth.cosineapi.service.ICalendarService;
import cn.globalyouth.cosineapi.common.utils.DateUtils;
import cn.globalyouth.cosineapi.common.utils.StringUtils;
import cn.globalyouth.cosineapi.model.vo.req.CalendarCustomReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CalendarEventReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;


import java.io.StringWriter;
import java.io.PrintWriter;



/**
 * @author liuyufeng, Rujun Yan
 * 日程服务
 */
@Service
@Slf4j
public class CalendarServiceImpl implements ICalendarService {

    @Resource
    private CalendarDao calendarDao;

    @Resource
    private MockInterviewDao mockInterviewDao;

    @Resource
    private JoinMockDao joinMockDao;

    @Resource
    private TeachInDao teachInDao;

    @Resource
    private IndustryDao industryDao;

    @Resource
    private UserDao userDao;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final int DAY = 1;
    private static final int WEEK = 2;

    /**
     * 添加事件到日历
     *
     * @param reqVo 事件请求对象
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> addCalendar(CalendarEventReqVo reqVo) {
        try {
            Calendar data = handleCalendar(reqVo);
            if (null == calendarDao.selectCalendar(data) && 1 == calendarDao.addCalendar(data)) {
                return ApiResponse.success(calendarDao.selectLastAdded(reqVo.getUser(), reqVo.getTitle()));
            }
            return ApiResponse.error(403, "Data existed.");
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 添加事件到日历
     *
     * @param reqVo 事件请求对象
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> addCalendar(CalendarCustomReqVo reqVo) {
        try {
            Calendar data = handleCalendar(reqVo);
            if (1 == calendarDao.addCalendar(data)) {
                return ApiResponse.success(calendarDao.selectLastAdded(reqVo.getUser(), reqVo.getTitle()));
            }
            return ApiResponse.error(403, "Data existed.");
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 从日历删除日程
     *
     * @param reqVo 事件数据
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> delCalendar(CalendarEventReqVo reqVo) {
        try {
            Calendar data = handleCalendar(reqVo);
            if (null != calendarDao.selectCalendar(data)) {
                if (1 == calendarDao.delCalendar(data)) {
                    return ApiResponse.success(null);
                }
                return ApiResponse.error(403, "Data absent.");
            }
            return ApiResponse.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 按id删除事件
     *
     * @param id 事件id
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> delCalendar(int id) {
        try {
            if (1 == calendarDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error(403, "Data absent.");

        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 修改日历事件数据
     *
     * @param reqVo 事件数据
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> updateCalendar(CalendarCustomReqVo reqVo) {
        try {
            Calendar data = handleCalendar(reqVo);
            if (1 == calendarDao.updateCalendar(data)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error(403, "Data error.");
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 列出当日日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param types 事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    @Override
    public ApiResponse<List<CalendarDto>> listDayEvents(int userId, String date, List<Integer> types) {
        try {
            List<CalendarDto> data = new ArrayList<>();
            Date fromTime = DATE_FORMAT.parse(date);
            Date toTime = new Date(fromTime.getTime() + 24 * 60 * 60 * 1000);
            return getListApiResponse(userId, types, data, fromTime, toTime, DAY);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 列出当日日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param styles 事件颜色
     * @return ApiResponse<List < CalendarDto>>
     */
    @Override
    public ApiResponse<List<CalendarDto>> listDayEventsV2(int userId, String date, List<Integer> styles) {
        try {
            List<CalendarDto> data = new ArrayList<>();
            Date fromTime = DATE_FORMAT.parse(date);
            Date toTime = new Date(fromTime.getTime() + 24 * 60 * 60 * 1000);
            return getListApiResponseV2(userId, styles, data, fromTime, toTime, DAY);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 列出本周日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param types 事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    @Override
    public ApiResponse<List<CalendarDto>> listWeekEvents(int userId, String date, List<Integer> types) {
        try {
            List<CalendarDto> data = new ArrayList<>();
            Date fromTime = DATE_FORMAT.parse(date);
            Date toTime = new Date(fromTime.getTime() + 7 * 24 * 60 * 60 * 1000);
            return getListApiResponse(userId, types, data, fromTime, toTime, WEEK);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 列出本周日程
     *
     * @param userId 用户id
     * @param date   日期
     * @param styles 事件种类
     * @return ApiResponse<List < CalendarDto>>
     */
    @Override
    public ApiResponse<List<CalendarDto>> listWeekEventsV2(int userId, String date, List<Integer> styles) {
        try {
            List<CalendarDto> data = new ArrayList<>();
            Date fromTime = DATE_FORMAT.parse(date);
            Date toTime = new Date(fromTime.getTime() + 7 * 24 * 60 * 60 * 1000);
            return getListApiResponseV2(userId, styles, data, fromTime, toTime, WEEK);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    private ApiResponse<List<CalendarDto>> getListApiResponse(int userId, List<Integer> types, List<CalendarDto> data,
                                                              Date fromTime, Date toTime, int code) {
        List<Calendar> events = calendarDao.listAll(userId, fromTime, toTime, types);
        events.addAll(listRepeat(userId, fromTime, code, types));
        events.sort(Comparator.comparing(Calendar::getFromTime));
        events.forEach(calendar -> data.add(handleCalendar(calendar)));
        return ApiResponse.success(data);
    }

    private ApiResponse<List<CalendarDto>> getListApiResponseV2(int userId, List<Integer> styles, List<CalendarDto> data,
                                                              Date fromTime, Date toTime, int code) {
        List<Calendar> events = calendarDao.listAllV2(userId, fromTime, toTime, styles);
        events.addAll(listRepeat(userId, fromTime, code, styles));
        events.sort(Comparator.comparing(Calendar::getFromTime));
        events.forEach(calendar -> data.add(handleCalendar(calendar)));
        return ApiResponse.success(data);
    }

    /**
     * 获取重复事件
     * @param userId 用户id
     * @param date 请求日期
     * @param code 日视图/周视图
     * @return List<Calendar>
     */
    private List<Calendar> listRepeat(int userId, Date date, int code, List<Integer> styles) {
        List<Calendar> res = new ArrayList<>();
        List<Calendar> repeatEvents;
        if (code == DAY) {
            repeatEvents = calendarDao.listRepeat(userId, date, styles);
            repeatEvents.forEach(calendar -> {
                if (RepeatEnum.DAILY.getCode() == calendar.getRepeat()) {
                    res.add(DateUtils.getDailyEvent(calendar, date));
                } else if (RepeatEnum.WEEKLY.getCode() == calendar.getRepeat()) {
                    Calendar c = DateUtils.getWeeklyEvent(calendar, date);
                    if (null != c) {
                        res.add(c);
                    }
                } else if (RepeatEnum.MONTHLY.getCode() == calendar.getRepeat()) {
                    Calendar c = DateUtils.getMonthlyEvent(calendar, date);
                    if (null != c) {
                        res.add(c);
                    }
                }
            });
        } else if (code == WEEK) {
            Date weekDate = new Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
            repeatEvents = calendarDao.listRepeat(userId, weekDate, styles);
            repeatEvents.forEach(calendar -> {
                if (RepeatEnum.DAILY.getCode() == calendar.getRepeat()) {
                    res.addAll(DateUtils.getDailyEvents(calendar, date));
                } else if (RepeatEnum.WEEKLY.getCode() == calendar.getRepeat()) {
                    Calendar c = DateUtils.getWeeklyEvents(calendar, date);
                    if (null != c) {
                        res.add(c);
                    }
                } else if (RepeatEnum.MONTHLY.getCode() == calendar.getRepeat()) {
                    Calendar c = DateUtils.getMonthlyEvents(calendar, date);
                    if (null != c) {
                        res.add(c);
                    }
                }
            });
        }
        return res;
    }

    private Calendar handleCalendar(CalendarEventReqVo reqVo) {
        Calendar res = new Calendar();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUser());
        res.setFromTime(new Timestamp(reqVo.getFromTime()));
        res.setToTime(new Timestamp(reqVo.getToTime()));
        res.setType(reqVo.getType());
        res.setEvent(reqVo.getEvent());
        res.setTitle(reqVo.getTitle());
        res.setStyle(reqVo.getStyle());
        res.setContent(reqVo.getContent());
        res.setRemark(reqVo.getRemark());
        res.setRemind(reqVo.getRemind());
        res.setZone(reqVo.getZone());
        res.setIsNode(reqVo.isNode() ? 1 : 0);
        res.setRepeat(0);
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private Calendar handleCalendar(CalendarCustomReqVo reqVo) {
        Calendar res = new Calendar();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUser());
        res.setFromTime(new Timestamp(reqVo.getFromTime().getTime()));
        res.setToTime(new Timestamp(reqVo.getToTime().getTime()));
        res.setType(reqVo.getType());
        res.setTitle(reqVo.getTitle());
        res.setStyle(reqVo.getStyle());
        res.setContent(reqVo.getContent());
        res.setRemind(reqVo.getRemind());
        res.setZone(reqVo.getZone());
        res.setIsNode(reqVo.isNode() ? 1 : 0);
        res.setRemark(reqVo.getRemark());
        res.setRepeat(reqVo.getRepeat());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private CalendarDto handleCalendar(Calendar data) {
        CalendarDto res = new CalendarDto();
        res.setId(data.getId());
        res.setDate(StringUtils.getDateStr(data.getFromTime()));
        res.setFromTime(StringUtils.getTimeStr(data.getFromTime()));
        res.setToTime(StringUtils.getTimeStr(data.getToTime()));
        res.setFullFromDate(StringUtils.getFullDateStr2(data.getFromTime()));
        res.setType(data.getType());
        res.setStyle(StyleEnum.getColorByCode(data.getStyle()));
        res.setRemind(data.getRemind());
        res.setZone(data.getZone());
        res.setTitle(data.getTitle());
        res.setContent(data.getContent());
        res.setRemark(null == data.getRemark() ? "" : data.getRemark());
        res.setNode(data.getIsNode() == 1);
        res.setRepeat(data.getRepeat());
        if (res.getType() == EventTypeEnum.MOCK.getCode() || res.getType() == EventTypeEnum.TEACH_IN.getCode()) {
            res.setEvent(handleEvent(data, res.getType()));
        }
        return res;
    }

    private CalendarDto.Event handleEvent(Calendar calendar, int type) {
        CalendarDto.Event res = new CalendarDto.Event();
        if (type == EventTypeEnum.MOCK.getCode()) {
            MockInterview mock = mockInterviewDao.selectById(calendar.getEvent());
            res.setType(industryDao.selectById(mock.getIndustry()));
            res.setLink(mock.getLink());
            res.setContent(mock.getContent());
            MockInterviewDto.Initiator initiator = new MockInterviewDto.Initiator();
            User u = userDao.selectInitiator(mock.getInitiator());
            initiator.setId(u.getId());
            initiator.setName(u.getName());
            initiator.setProfile(u.getProfile());
            initiator.setIntroduction(u.getIntroduction());
            res.setInitiator(initiator);
            List<MockInterviewDto.Participant> participants = new ArrayList<>();
            List<JoinMock> joins = joinMockDao.listMockUsers(mock.getId());
            List<User> ps = userDao.selectParticipant(joins.stream().map(JoinMock::getUser).collect(Collectors.toList()));
            ps.forEach(user -> {
                MockInterviewDto.Participant p = new MockInterviewDto.Participant();
                p.setId(user.getId());
                p.setProfile(user.getProfile());
                participants.add(p);
            });
            res.setParticipants(participants);
        } else if (type == EventTypeEnum.TEACH_IN.getCode()) {
            TeachIn teachIn = teachInDao.selectById(calendar.getEvent());
            res.setType(industryDao.selectById(teachIn.getIndustry()));
            res.setPeople(teachIn.getPeople());
            res.setLink(teachIn.getLink());
            res.setContent(teachIn.getContent());
            MockInterviewDto.Initiator initiator = new MockInterviewDto.Initiator();
            User u = userDao.selectInitiator(teachIn.getInitiator());
            initiator.setId(u.getId());
            initiator.setName(u.getName());
            initiator.setProfile(u.getProfile());
            initiator.setIntroduction(u.getIntroduction());
            res.setInitiator(initiator);
        }
        return res;
    }

}
