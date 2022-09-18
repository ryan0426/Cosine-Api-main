package cn.globalyouth.cosineapi.dao.teachin;

import cn.globalyouth.cosineapi.model.bean.teachin.TeachIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_teachin 宣讲会dao
 */
@Mapper
public interface TeachInDao {

    /**
     * 查询尚未开始的所有宣讲会
     *
     * @return List<TeachIn>
     */
    @Select("select * from tb_teachin where time >= now() order by time limit 20")
    @Results(id = "teachInMapper", value = {
            @Result(column = "is_vip", property = "isVip")
    })
    List<TeachIn> listAll();

    /**
     * 按名称查询尚未开始的宣讲会
     *
     * @param name 名称
     * @return List<TeachIn>
     */
    @Select("select * from tb_teachin where title like '%${title}%' and time >= now() order by time limit 20")
    @ResultMap("teachInMapper")
    List<TeachIn> listByName(String name);

    /**
     * 查询VIP专属宣讲会
     *
     * @return List<TeachIn>
     */
    @Select("select * from tb_teachin where is_vip=1 and time >= now() order by time limit 20")
    @ResultMap("teachInMapper")
    List<TeachIn> listVip();

    /**
     * 按名称查询尚未开始的VIP宣讲会
     *
     * @param name 名称
     * @return List<TeachIn>
     */
    @Select("select * from tb_teachin where title like '%${title}%' and is_vip=1 and time >= now() order by time limit 20")
    @ResultMap("teachInMapper")
    List<TeachIn> listVipByName(String name);

    /**
     * 根据id查询
     *
     * @param id 数据id
     * @return TeachIn
     */
    @Select("select * from tb_teachin where id=#{id}")
    TeachIn selectById(int id);
}
