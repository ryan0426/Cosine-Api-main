package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.common.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_job sql生成
 */
public class JobSql {

    public String listByConditions(
        @Param("locations") List<String> locations,
        @Param("industries") List<String> industries,
        @Param("jobNatures") List<String> jobNatures,
        @Param("educations") List<String> educations,
        @Param("salaries") Integer salaries,
        @Param("companyNatures") List<String> companyNatures,
        @Param("name") String name,
        @Param("haiTou") boolean haiTou,
        @Param("offset") int offset,
        @Param("count") int count
        ) {
        SQL sql = new SQL();
        sql.SELECT("j.*").FROM("tb_job j").JOIN("tb_company c on j.company_id=c.id");
        if (notEmpty(locations)) {
            sql.WHERE("j.location in " + generateInCondition(locations));
        }
        if (notEmpty(industries)) {
            sql.WHERE("c.industry in " + generateInCondition(industries));
        }
        if (notEmpty(jobNatures)) {
            sql.WHERE("j.nature in " + generateInCondition(jobNatures));
        }
        if (notEmpty(educations)) {
            sql.WHERE("j.education in " + generateInCondition(educations));
        }
        if (salaries != null) {
            sql.WHERE("j.max_salary>=#{salaries}");
        }
        if (notEmpty(companyNatures)) {
            sql.WHERE("c.nature in " + generateInCondition(companyNatures));
        }
        if (StringUtils.notEmpty(name)) {
            sql.AND().WHERE("j.title like #{name} or c.name like #{name}");
        }
        if (haiTou) {
            sql.AND().WHERE("j.can_online is null");
        }
        sql.ORDER_BY("add_time desc").LIMIT("#{offset}" + "," + "#{count}");
        return sql.toString();
    }

    public String listByConditions2(
        @Param("locations") List<String> locations, 
        @Param("industries") List<String> industries, 
        @Param("jobNatures") List<String> jobNatures, 
        @Param("count") int count) {
        SQL sql = new SQL();
        sql.SELECT("j.*").FROM("tb_job j").JOIN("tb_company c on j.company_id=c.id");
        if (notEmpty(locations)) {
            sql.WHERE("j.location in " + generateInCondition(locations));
        }
        if (notEmpty(industries)) {
            sql.WHERE("c.industry in " + generateInCondition(industries));
        }
        if (notEmpty(jobNatures)) {
            sql.WHERE("j.nature in " + generateInCondition(jobNatures));
        }
        sql.ORDER_BY("send_cnt desc", "add_time desc").LIMIT("#{count}");
        return sql.toString();
    }

    private boolean notEmpty(List<String> list) {
        return null != list && list.size() != 0;
    }

    private String generateInCondition(List<String> list) {
        StringBuilder con = new StringBuilder();
        con.append("(");
        for (String str : list) {
            con.append("'");
            con.append(str);
            con.append("',");
        }
        con.replace(con.length() - 1, con.length(), ")");
        return con.toString();
    }
}
