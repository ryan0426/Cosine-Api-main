package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.*;
import org.apache.ibatis.jdbc.SQL;

import static cn.globalyouth.cosineapi.common.utils.StringUtils.notEmpty;

/**
 * @author liuyufeng
 * 个人履历库 sql生成
 */
public class RecordSql {

    public String updateActivity(RecordActivity data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_activity");
        if (notEmpty(data.getActivity())) {
            sql.SET("activity=#{activity}");
        }
        if (notEmpty(data.getRole())) {
            sql.SET("role=#{role}");
        }
        if (notEmpty(data.getDepartment())) {
            sql.SET("department=#{department}");
        }
        if (notEmpty(data.getCity())) {
            sql.SET("city=#{city}");
        }
        if (null != data.getFromDate()) {
            sql.SET("from_date=#{fromDate}");
        }
        if (null != data.getToDate()) {
            sql.SET("to_date=#{toDate}");
        }
        if (notEmpty(data.getDescription())) {
            sql.SET("description=#{description}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }

    public String updateAward(RecordAward data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_award");
        if (notEmpty(data.getAward())) {
            sql.SET("award=#{award}");
        }
        if (notEmpty(data.getLevel())) {
            sql.SET("level=#{level}");
        }
        if (null != data.getDate()) {
            sql.SET("date=#{date}");
        }
        if (notEmpty(data.getDescription())) {
            sql.SET("description=#{description}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }

    public String updateBasic(RecordBasic data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_basic");
        if (notEmpty(data.getName())) {
            sql.SET("name=#{name}");
        }
        if (null != data.getGender()) {
            sql.SET("gender=#{gender}");
        }
        if (notEmpty(data.getPhone())) {
            sql.SET("phone=#{phone}");
        }
        if (notEmpty(data.getEmail())) {
            sql.SET("email=#{email}");
        }
        if (notEmpty(data.getLocation())) {
            sql.SET("location=#{location}");
        }
        if (notEmpty(data.getOther())) {
            sql.SET("other=#{other}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }

    public String updateEducation(RecordEducation data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_education");
        if (notEmpty(data.getSchool())) {
            sql.SET("school=#{school}");
        }
        if (notEmpty(data.getEducation())) {
            sql.SET("education=#{education}");
        }
        if (notEmpty(data.getMajor())) {
            sql.SET("major=#{major}");
        }
        if (notEmpty(data.getMinor())) {
            sql.SET("minor=#{minor}");
        }
        if (notEmpty(data.getCollege())) {
            sql.SET("college=#{college}");
        }
        if (null != data.getFromDate()) {
            sql.SET("from_date=#{fromDate}");
        }
        if (null != data.getToDate()) {
            sql.SET("to_date=#{toDate}");
        }
        if (notEmpty(data.getDescription())) {
            sql.SET("description=#{description}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }

    public String updateOther(RecordOther data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_other");
        if (notEmpty(data.getSkill())) {
            sql.SET("skill=#{skill}");
        }
        if (notEmpty(data.getLanguage())) {
            sql.SET("language=#{language}");
        }
        if (notEmpty(data.getCertificate())) {
            sql.SET("certificate=#{certificate}");
        }
        if (notEmpty(data.getHobby())) {
            sql.SET("hobby=#{hobby}");
        }
        if (notEmpty(data.getDescription())) {
            sql.SET("description=#{description}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }

    public String updateWork(RecordWork data) {
        SQL sql = new SQL();
        sql.UPDATE("tb_record_work");
        if (notEmpty(data.getCompany())) {
            sql.SET("company=#{company}");
        }
        if (notEmpty(data.getPosition())) {
            sql.SET("position=#{position}");
        }
        if (notEmpty(data.getDepartment())) {
            sql.SET("department=#{department}");
        }
        if (notEmpty(data.getCity())) {
            sql.SET("city=#{city}");
        }
        if (null != data.getFromDate()) {
            sql.SET("from_date=#{fromDate}");
        }
        if (null != data.getToDate()) {
            sql.SET("to_date=#{toDate}");
        }
        if (notEmpty(data.getDescription())) {
            sql.SET("description=#{description}");
        }
        if (null != data.getAddTime()) {
            sql.SET("add_time=#{addTime}");
        }
        if (null != data.getId()) {
            sql.WHERE("id=#{id}");
        } else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }
}
