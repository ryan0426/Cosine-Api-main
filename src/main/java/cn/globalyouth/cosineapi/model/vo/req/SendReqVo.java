package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 投递请求
 */
@Data
public class SendReqVo {

    private int userId;

    private int resumeId;

    private String resumeTitle;

    private int companyId;

    private int jobId;

    private long addTime;
}
