package cn.globalyouth.cosineapi.model.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng, Rujun Yan
 * 微信小程序模版消息 自定义事件
 */
@Data
public class CustomMessageReqVo {

    private String touser;

    private String template_id = "q_GNZZREX7p4MzM9PrQV-ooJOT1H9nXr-6MduOBc3Yk";

    private String page = "pages/calendar/calendar";

    private String miniprogram_state = "developer";

    private String lang = "zh_CN";

    private Param data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Param {

        private Value thing1;

        private Value time2;

        private Value thing3;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Value {

        private String value;

    }

}
