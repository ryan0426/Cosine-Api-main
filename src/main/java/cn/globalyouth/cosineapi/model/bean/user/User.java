package cn.globalyouth.cosineapi.model.bean.user;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_user 用户
 */
@Data
public class User {

    private Integer id;

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

    private Integer vip;

    private Integer authority;

    private Timestamp addTime;

    private Timestamp loginTime;

}
