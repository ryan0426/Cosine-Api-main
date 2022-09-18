package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.vo.req.ActivityMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.req.BindMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CustomMessageReqVo;

/**
 * @author liuyufeng, Rujun Yan
 * 微信小程序服务端
 */
public interface IWechatService {

    /**
     * 发送给定事件订阅消息
     *
     * @param message 消息对象
     */
    void sendBindMessage(BindMessageReqVo message);

    /**
     * 发送自定义事件订阅消息
     *
     * @param message 消息对象
     */
    void sendCustomMessage(CustomMessageReqVo message);

    /**
     * 发送活动即将截止订阅消息
     *
     * @param message 消息对象
     */
    void sendActivityMessage(ActivityMessageReqVo message);
}
