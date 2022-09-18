package cn.globalyouth.cosineapi.model.dto.user;

import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 用户dto
 */
@Data
public class UserDto {

    private int id;

    private String name;

    private Integer gender;

    private String profile;

    private String openid;

    private String phone;

    private String password;

    private String school;

    private String major;

    private Integer grade;

    private String signature;

    private String introduction;

    private int vip;
}
