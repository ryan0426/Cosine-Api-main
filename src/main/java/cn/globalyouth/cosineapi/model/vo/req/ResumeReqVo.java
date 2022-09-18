package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 简历导入
 */
@Data
public class ResumeReqVo {

    private Integer userId;

    private String title;

    private String recordValues;

    private String key;

    private Boolean isDefault;

    private long addTime;
}
