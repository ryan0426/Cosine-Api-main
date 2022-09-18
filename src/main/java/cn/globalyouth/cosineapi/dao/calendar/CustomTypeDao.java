package cn.globalyouth.cosineapi.dao.calendar;

import cn.globalyouth.cosineapi.model.bean.calendar.CustomType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liuyufeng
 * tb_custom_type 自定义事件类型dao
 */
@Mapper
public interface CustomTypeDao {

    /**
     * 查询所有自定义事件类型
     *
     * @param userId 用户id
     * @return List<CustomStyle>
     */
    @Select("select * from tb_custom_type where user=#{userId} order by style")
    List<CustomType> listAll(int userId);

    /**
     * 查找对应自定义事件类型id
     *
     * @param data 数据
     * @return id号
     */
    @Select("select id from tb_custom_type where user=#{user} and style=#{style}")
    Integer selectStyle(CustomType data);

    /**
     * 添加自定义事件类型
     *
     * @param data 自定义事件
     * @return 0/1
     */
    @Insert("insert into tb_custom_type(user,style,name) values (#{user},#{style},#{name})")
    int insertStyle(CustomType data);

    /**
     * 更改自定义事件类型名
     *
     * @param id   数据记录id
     * @param name 名称
     * @return 0/1
     */
    @Update("update tb_custom_type set name=#{name} where id=#{id}")
    int updateStyle(int id, String name);
}
