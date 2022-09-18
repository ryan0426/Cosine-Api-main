package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 手机号登录
 */
@Data
public class PhoneLoginReqVo {

    private String openid;

    private String phone;
}
