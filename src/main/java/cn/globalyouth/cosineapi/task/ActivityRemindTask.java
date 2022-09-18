package cn.globalyouth.cosineapi.task;

import cn.globalyouth.cosineapi.dao.user.UserDao;
import cn.globalyouth.cosineapi.service.IWechatService;
import cn.globalyouth.cosineapi.model.vo.req.ActivityMessageReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 活动结束提醒定时任务
 */
@Component
@Slf4j
public class ActivityRemindTask {

    @Resource
    private UserDao userDao;

    @Resource
    private IWechatService wechatService;

    @Scheduled(cron = "0 0 10 25 4 ?")
    public void send() {
        List<String> openidList = userDao.listAllOpenid();
        log.info("用户数量共{}个", openidList.size());
        for (String openid : openidList) {
            ActivityMessageReqVo message = new ActivityMessageReqVo();
            message.setTouser(openid);
            ActivityMessageReqVo.Param data = new ActivityMessageReqVo.Param();
            data.setThing1(new ActivityMessageReqVo.Value("《2021全球留学生招聘季》"));
            data.setTime2(new ActivityMessageReqVo.Value("2021年4月27日 23:59"));
            data.setThing3(new ActivityMessageReqVo.Value("距结束还有3天，准备好简历赶紧投递吧"));
            wechatService.sendActivityMessage(message);
        }
    }
}
