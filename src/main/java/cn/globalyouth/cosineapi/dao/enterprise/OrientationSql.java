package cn.globalyouth.cosineapi.dao.enterprise;

import cn.globalyouth.cosineapi.common.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * tb_enterprise_orientation sql生成器
 */
public class OrientationSql {

  public String selectLike(String name) {
    SQL sql = new SQL();
    sql.SELECT("*").FROM("tb_enterprise_orientation");
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
      .WHERE("name like " + query.toString());
      //.OR()
      //.WHERE("comment like " + query.toString());
    //sql.LIMIT(5);
    return sql.toString();
  }
}