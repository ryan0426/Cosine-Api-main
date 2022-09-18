package cn.globalyouth.cosineapi.task;

import cn.globalyouth.cosineapi.service.IWechatService;
import cn.globalyouth.cosineapi.service.impl.WechatServiceImpl;
import cn.globalyouth.cosineapi.model.vo.req.BindMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CustomMessageReqVo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author liuyufeng, Rujun Yan
 * 微信小程序发送模版消息
 */
@Slf4j
public class MessageJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        IWechatService service = new WechatServiceImpl();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Object messageReq = dataMap.get("message");
        if (messageReq instanceof BindMessageReqVo) {
            log.info("发送给定事件消息通知: " + messageReq.toString());
            service.sendBindMessage((BindMessageReqVo) messageReq);
        } else if (messageReq instanceof CustomMessageReqVo) {
            log.info("发送自定义事件消息通知: " + messageReq.toString());
            service.sendCustomMessage((CustomMessageReqVo) messageReq);
        }
    }
}
