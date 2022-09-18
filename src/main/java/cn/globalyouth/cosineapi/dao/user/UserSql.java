package cn.globalyouth.cosineapi.dao.user;

import cn.globalyouth.cosineapi.model.bean.user.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static cn.globalyouth.cosineapi.common.utils.StringUtils.*;

/**
 * @author liuyufeng, Rujun Yan
 * 用户sql生成类
 */
public class UserSql {

    public String selectUsers(List<Integer> ids) {
        SQL sql = new SQL();
        StringBuilder condition = new StringBuilder();
        condition.append("id in (");
        for (int id : ids) {
            condition.append(id);
            condition.append(",");
        }
        condition.deleteCharAt(condition.length() - 1);
        condition.append(")");
        sql.SELECT("id, profile").FROM("tb_user").WHERE(condition.toString());
        return sql.toString();
    }

    public String update(User user) {
        SQL sql = new SQL();
        sql.UPDATE("tb_user");
        if (notEmpty(user.getName())) {
            sql.SET("name=#{name}");
        }
        if (null != user.getGender() && 0 != user.getGender()) {
            sql.SET("gender=#{gender}");
        }
        if (notEmpty(user.getProfile())) {
            sql.SET("profile=#{profile}");
        }
        if (notEmpty(user.getPhone())) {
            sql.SET("phone=#{phone}");
        }
        if (notEmpty(user.getPassword())) {
            sql.SET("password=#{password}");
        }
        if (notEmpty(user.getSchool())) {
            sql.SET("school=#{school}");
        }
        if (notEmpty(user.getMajor())) {
            sql.SET("major=#{major}");
        }
        if (null != user.getGrade() && 0 != user.getGrade()) {
            sql.SET("grade=#{grade}");
        }
        if (notEmpty(user.getSignature())) {
            sql.SET("signature=#{signature}");
        }
        if (notEmpty(user.getIntroduction())) {
            sql.SET("introduction=#{introduction}");
        }
        if (null != user.getVip()) {
            sql.SET("vip=#{vip}");
        }
        if (null != user.getAuthority()) {
            sql.SET("authority=#{authority}");
        }
        if (null != user.getLoginTime()) {
            sql.SET("login_time=#{loginTime}");
        }
        if (null != user.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }
}
