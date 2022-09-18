package cn.globalyouth.cosineapi.dao.type;

import cn.globalyouth.cosineapi.model.bean.type.Zone;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuyufeng, Rujun Yan
 * tb_zone 时区dao
 */
@Mapper
public interface ZoneDao {
  /**
   * 列出全部时区
   *
   * @return List<Zone>
   */
  @Select("select * from tb_zone")
  List<Zone> listAll();

  /**
   * 查询一个时区
   *
   * @param id 时区id
   * @return Zone
   */
  @Select("select * from tb_zone where id=#{id}")
  Zone selectById(int id);
}
