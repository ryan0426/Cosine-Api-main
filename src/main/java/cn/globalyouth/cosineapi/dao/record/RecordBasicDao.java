package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_basic 履历 基本信息dao
 */
@Mapper
public interface RecordBasicDao {

    /**
     * 添加基本信息
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_basic(user,name,gender,phone,email,location,other,add_time) values " +
            "(#{user},#{name},#{gender},#{phone},#{email},#{location},#{other},#{addTime})")
    int addBasic(RecordBasic data);

    /**
     * 修改基本信息
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateBasic")
    int updateBasic(RecordBasic data);

    /**
     * 查询最新基础信息
     *
     * @param userId 用户id
     * @return RecordBasic
     */
    @Select("select * from tb_record_basic where user=#{userId} order by add_time desc limit 1")
    RecordBasic selectLatestBasic(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordBasic
     */
    @Select("select * from tb_record_basic where id=#{id}")
    RecordBasic selectById(int id);
}
