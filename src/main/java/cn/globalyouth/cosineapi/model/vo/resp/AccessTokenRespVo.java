package cn.globalyouth.cosineapi.model.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author liuyufeng
 * 微信小程序access_token响应
 */
@Data
public class AccessTokenRespVo {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}
