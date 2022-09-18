package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.enums.WechatConstant;
import cn.globalyouth.cosineapi.service.IWechatService;
import cn.globalyouth.cosineapi.model.vo.req.ActivityMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.req.BindMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CustomMessageReqVo;
import cn.globalyouth.cosineapi.model.vo.resp.AccessTokenRespVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * @author liuyufeng, Rujun Yan 微信小程序服务端
 */
@Service
@Slf4j
public class WechatServiceImpl implements IWechatService {

    private static String accessToken;
    private static long lastTime;
    private static long expiresTime;

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + WechatConstant.APP_ID + "&secret=" + WechatConstant.APP_SECRET;
    private static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    private static final ObjectMapper OM = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 发送给定事件订阅消息
     *
     * @param message 消息对象
     */
    @Override
    public void sendBindMessage(BindMessageReqVo message) {
        try {
            checkAccessToken();
            String url = MESSAGE_URL + accessToken;
            String resp = restTemplate.postForObject(url, message, String.class);
            log.info("发送消息响应: " + resp);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
        }
    }

    /**
     * 发送自定义事件订阅消息
     *
     * @param message 消息对象
     */
    @Override
    public void sendCustomMessage(CustomMessageReqVo message) {
        try {
            checkAccessToken();
            String url = MESSAGE_URL + accessToken;
            String resp = restTemplate.postForObject(url, message, String.class);
            log.info("发送消息响应: " + resp);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
        }
    }

    @Override
    public void sendActivityMessage(ActivityMessageReqVo message) {
        try {
            checkAccessToken();
            String url = MESSAGE_URL + accessToken;
            String resp = restTemplate.postForObject(url, message, String.class);
            log.info("发送消息响应: " + resp);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
        }
    }

    /**
     * 检查并获取access_token
     */
    private void checkAccessToken() throws JsonProcessingException {
        if (null == accessToken) {
            getAccessToken();
            return;
        }
        long pastTime = System.currentTimeMillis() - lastTime;
        if (pastTime >= expiresTime) {
            getAccessToken();
        }
    }

    private void getAccessToken() throws JsonProcessingException {
        String resp = restTemplate.getForObject(ACCESS_TOKEN_URL, String.class);
        AccessTokenRespVo accessTokenResp = OM.readValue(resp, AccessTokenRespVo.class);
        accessToken = accessTokenResp.getAccessToken();
        lastTime = System.currentTimeMillis();
        expiresTime = accessTokenResp.getExpiresIn();
        log.info("access_token: " + accessTokenResp.toString());
    }

}
