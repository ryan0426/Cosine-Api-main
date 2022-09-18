package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng
 * 修改个人信息Vo
 */
@Data
public class UserInfoReqVo {

    private int id;

    private String profile;

    private String name;

    private int gender;

    private String school;

    private String major;

    private int grade;

    private String signature;

    private Long loginTime;
}
