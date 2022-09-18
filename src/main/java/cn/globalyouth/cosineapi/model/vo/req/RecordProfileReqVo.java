package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 履历 头像请求对象
 */
@Data
public class RecordProfileReqVo {

    private int userId;

    private String profile;

    private long addTime;

}
