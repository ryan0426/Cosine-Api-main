package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 批量投递
 */
@Data
public class SendMultiReqVo {

    private int userId;

    private int resumeId;

    private String resumeTitle;

    private List<Job> jobs;

    private long addTime;

    @Data
    public static class Job {

        private int companyId;

        private int jobId;
    }
}
