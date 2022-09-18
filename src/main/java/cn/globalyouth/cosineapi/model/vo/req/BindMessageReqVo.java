package cn.globalyouth.cosineapi.model.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng
 * 微信小程序模版消息 给定事件
 */
@Data
public class BindMessageReqVo {

    private String touser;

    private String template_id = "q_GNZZREX7p4MzM9PrQV-uJUr-_Vm_iA16P_cW6da04";

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

        private Value thing12;

        private Value thing13;

        private Value thing6;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Value {

        private String value;

    }

}
