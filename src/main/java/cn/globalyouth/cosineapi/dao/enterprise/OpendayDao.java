package cn.globalyouth.cosineapi.dao.enterprise;

import cn.globalyouth.cosineapi.model.bean.enterprise.Openday;
import org.apache.ibatis.annotations.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 * tb_enterprise_openday 开放日dao
 */
@Mapper
public interface OpendayDao {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

    /**
    * 添加开放日
    *
    * @param openday 开放日
    * @return 0/1
    */
    @Insert("insert into tb_enterprise_openday(image,name,description,month,day,startTime,endTime,location,survey_url)" +
            "values (#{image},#{name},#{description},#{month},#{day},#{startTime},#{endTime},#{location},#{survey_url})")
    int addOpenday(Openday openday);

    /**
    * 删除开放日
    *
    * @param id 开放日id
    * @return 0/1
    */
    @Delete("delete from tb_enterprise_openday where id=#{id}")
    int deleteOpenday(int id);

    /**
    * 获取开放日列表
    *
    * @return List<Openday>
    */
    @Select("select * from tb_enterprise_openday")
    List<Openday> listOpenday();

    /**
    * 搜索开放日
    *
    * @param name 模糊查询匹配值
    * @return List<Openday>
    */
    //@Select("select * from tb_enterprise_openday where name like #{name}")
    @SelectProvider(type = OpendaySql.class, method = "selectLike")
    List<Openday> searchOpenday(String name);

    /**
    * 筛选开放日
    *
    * @param location 地点
    * @param month 开放日年月
    * @return List<Openday>
    */
    //@Select("select * from tb_enterprise_openday where location=#{location} and month=#{date}")
    @SelectProvider(type = OpendaySql.class, method = "select")
    List<Openday> filterOpenday(
        @Param("location") String location, 
        @Param("date") Date date);

    @Select("select * from tb_enterprise_openday where month=#{date}")
    List<Openday> filterOpendayMonth(Date date);

    @SelectProvider(type = OpendaySql.class, method = "filterLoc")
    List<Openday> filterOpendayLoc(String location);

}