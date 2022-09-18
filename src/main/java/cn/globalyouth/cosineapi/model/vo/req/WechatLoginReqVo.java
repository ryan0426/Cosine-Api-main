package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng
 * 微信登录请求Vo
 */
@Data
public class WechatLoginReqVo {

    private String openid;

    private String name;

    private String profile;

    private int gender;
}
