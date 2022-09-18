package cn.globalyouth.cosineapi.model.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng, Rujun Yan
 * 活动信息请求对象
 */
@Data
public class ActivityMessageReqVo {

    private String touser;

    private String template_id = "4O9uqO-oTyLwHOjYoszkhwXl3zmWBn1S1lmlLvW1MxI";

    private String page = "pages/send/send";

    private String miniprogram_state = "formal";

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
