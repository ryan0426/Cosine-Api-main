package cn.globalyouth.cosineapi.dao.enterprise;

import cn.globalyouth.cosineapi.common.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.annotations.*;
import java.util.Date;

/**
 * tb_enterprise_openday sql生成器
 */
public class OpendaySql {

  public String selectLike(String name) {
    SQL sql = new SQL();
    sql.SELECT("*").FROM("tb_enterprise_openday");
    if (!StringUtils.notEmpty(name)) {
      sql.WHERE("1=2");
      return sql.toString();
    }
    StringBuilder query = new StringBuilder();
    query.append("'%");
    for (int i = 0; i < name.length(); i++) {
      query.append(name.charAt(i));
      query.append("%");
    }
    query.append("'");
    sql
      .WHERE("name like " + query.toString())
      .OR()
      .WHERE("location like " + query.toString());
    //sql.LIMIT(5);
    return sql.toString();
  }

  public String select(@Param("location") String location, @Param("date") Date date) {
    SQL sql = new SQL();
    sql.SELECT("*").FROM("tb_enterprise_openday");
    if (!StringUtils.notEmpty(location)) {
      sql.WHERE("1=2");
      return sql.toString();
    }
    StringBuilder query = new StringBuilder();
    query.append("'%");
    for (int i = 0; i < location.length(); i++) {
      query.append(location.charAt(i));
      query.append("%");
    }
    query.append("'");
    sql
      .WHERE("location like " + query.toString())
      .WHERE("month=#{date}");
    return sql.toString();
  }

  public String filterLoc(String location) {
    SQL sql = new SQL();
    sql.SELECT("*").FROM("tb_enterprise_openday");
    if (!StringUtils.notEmpty(location)) {
      sql.WHERE("1=2");
      return sql.toString();
    }
    StringBuilder query = new StringBuilder();
    query.append("'%");
    for (int i = 0; i < location.length(); i++) {
      query.append(location.charAt(i));
      query.append("%");
    }
    query.append("'");
    sql
      .WHERE("location like " + query.toString());
    return sql.toString();
  }
}