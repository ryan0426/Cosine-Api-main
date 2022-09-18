package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.common.utils.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * tb_send sql生成器
 */
public class SendSql {
  public String selectLike(@Param("userId") int userId, @Param("sortType") int sortType, @Param("name") String name) {
    SQL sql = new SQL();
    sql.SELECT("s.*").FROM("tb_send s").JOIN("tb_company c on s.company=c.id")
    .JOIN("tb_job j on s.job=j.id").WHERE("s.user=#{userId}");
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
      .WHERE("c.name like " + query.toString())
      .OR()
      .WHERE("j.title like " + query.toString())
      .ORDER_BY("add_time desc");
    //sql.LIMIT(5);
    return sql.toString();
  }
}