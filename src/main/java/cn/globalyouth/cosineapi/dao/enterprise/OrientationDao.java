package cn.globalyouth.cosineapi.dao.enterprise;

import cn.globalyouth.cosineapi.model.bean.enterprise.Orientation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * tb_enterprise_orientation 宣讲会dao
 */
@Mapper
public interface OrientationDao {

    /**
    * 添加宣讲会
    *
    * @param orientation 宣讲会
    * @return 0/1
    */
    @Insert("insert into tb_enterprise_orientation(image,name,description,date,startTime,endTime)" +
            "values (#{image},#{name},#{description},#{date},#{startTime},#{endTime})")
    int addOrientation(Orientation orientation);

    /**
    * 删除宣讲会
    *
    * @param id 宣讲会id
    * @return 0/1
    */
    @Delete("delete from tb_enterprise_orientation where id=#{id}")
    int deleteOrientation(int id);

    /**
    * 获取宣讲会列表
    *
    * @return List<Orientation>
    */
    @Select("select * from tb_enterprise_orientation")
    List<Orientation> listOrientation();

    /**
    * 搜索宣讲会
    *
    * @param name 模糊查询匹配值
    * @return List<Orientation>
    */
    //@Select("select * from tb_enterprise_orientation where name like #{name}")
    @SelectProvider(type = OrientationSql.class, method = "selectLike")
    List<Orientation> searchOrientation(String name);

}