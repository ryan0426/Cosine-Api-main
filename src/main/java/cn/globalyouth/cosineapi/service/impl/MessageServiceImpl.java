package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IMessageService;
import cn.globalyouth.cosineapi.task.MessageJob;
import cn.globalyouth.cosineapi.model.vo.req.*;

import static cn.globalyouth.cosineapi.common.utils.StringUtils.*;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;




import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * @author liuyufeng, Rujun Yan
 * 订阅消息服务
 */
@Service
@Slf4j
public class MessageServiceImpl implements IMessageService {

    /**
     * 订阅自定义事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     * TODO:不同时区时间换算
     */
    @Override
    public ApiResponse<Object> subscribeCustomMessage(CustomMessageJobReqVo reqVo) {
        // 设置服务请求参数
        CustomMessageReqVo message = new CustomMessageReqVo();
        String title = subString(reqVo.getTitle(), 20);
        String time = getFullDateStr(reqVo.getTime()) + " " + getTimeStr(reqVo.getTime());
        String content = subString(reqVo.getContent(), 20);
        CustomMessageReqVo.Value thing1 = new CustomMessageReqVo.Value(title);
        CustomMessageReqVo.Value time2 = new CustomMessageReqVo.Value(time);
        CustomMessageReqVo.Value thing3 = new CustomMessageReqVo.Value(content);
        CustomMessageReqVo.Param data = new CustomMessageReqVo.Param(thing1, time2, thing3);
        message.setData(data);
        message.setTouser(reqVo.getOpenid());
        log.info("添加自定义消息定时任务: " + message.toString());
        // 设置定时任务参数
        String key = reqVo.getUserId() + "C" + reqVo.getCalendarId();
        Date date = new Date(reqVo.getTime().getTime() - (long) reqVo.getRemind() * 60 * 1000);
        Map<String, Object> param = new HashMap<>(2);
        param.put("message", message);
        try {
            addTask(new MessageJob(), param, key, date);
            return ApiResponse.success(null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 订阅预置事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     * TODO:不同时区时间换算
     */
    @Override
    public ApiResponse<Object> subscribeBindMessage(BindMessageJobReqVo reqVo) {
        // 设置服务请求参数
        BindMessageReqVo message = new BindMessageReqVo();
        String title = subString(reqVo.getTitle(), 20);
        String time = getFullDateStr(new Date(reqVo.getTime())) + " " + getTimeStr(new Date(reqVo.getTime()));
        String way = subString(reqVo.getWay(), 20);
        String link = subString(reqVo.getLink(), 20);
        String remark = subString(reqVo.getRemark(), 20);
        BindMessageReqVo.Value thing1 = new BindMessageReqVo.Value(title);
        BindMessageReqVo.Value time2 = new BindMessageReqVo.Value(time);
        BindMessageReqVo.Value thing12 = new BindMessageReqVo.Value(way);
        BindMessageReqVo.Value thing13 = new BindMessageReqVo.Value(link);
        BindMessageReqVo.Value thing6 = new BindMessageReqVo.Value(remark);
        BindMessageReqVo.Param data = new BindMessageReqVo.Param(thing1, time2, thing12, thing13, thing6);
        message.setData(data);
        message.setTouser(reqVo.getOpenid());
        log.info("添加预置消息定时任务: " + message.toString());
        // 设置定时任务参数
        String key = reqVo.getUserId() + "B" + reqVo.getCalendarId();
        Date date = new Date(reqVo.getTime() - (long) reqVo.getRemind() * 60 * 1000);
        Map<String, Object> param = new HashMap<>(2);
        param.put("message", message);
        try {
            addTask(new MessageJob(), param, key, date);
            return ApiResponse.success(null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 取消订阅事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> cancelMessage(CancelMessageJobReqVo reqVo) {
        try {
            String key = reqVo.getUserId() + reqVo.getTypeChar() + reqVo.getCalendarId();
            delTask(key);
            log.info("删除任务: " + key);
            return ApiResponse.success(null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 更改自定义事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> changeCustomMessage(CustomMessageJobReqVo reqVo) {
        try {
            delTask(reqVo.getUserId() + "C" + reqVo.getCalendarId());
            return subscribeCustomMessage(reqVo);
        } catch (SchedulerException e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 更改预置事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> changeBindMessage(BindMessageJobReqVo reqVo) {
        try {
            delTask(reqVo.getUserId() + "B" + reqVo.getCalendarId());
            return subscribeBindMessage(reqVo);
        } catch (SchedulerException e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 创建任务
     */
    private void addTask(Job job, Map<String, Object> param, String key, Date date) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 开始
        scheduler.start();
        // job唯一标识
        JobKey jobKey = new JobKey(key);
        // 检查任务是否已存在
        if (scheduler.checkExists(jobKey)) {
            return;
        }
        // 任务参数
        JobDataMap map = new JobDataMap(param);
        // 任务实例
        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(jobKey).setJobData(map).build();
        // 执行时间
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(key).startAt(date).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 移除任务
     */
    private void delTask(String key) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.deleteJob(new JobKey(key));
    }
}
