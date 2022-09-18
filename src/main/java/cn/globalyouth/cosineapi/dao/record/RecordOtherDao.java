package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordOther;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_other 履历 其他dao
 */
@Mapper
public interface RecordOtherDao {

    /**
     * 添加其他
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_other(user,skill,language,certificate,hobby,description,add_time) values " +
            "(#{user},#{skill},#{language},#{certificate},#{hobby},#{description},#{addTime})")
    int addOther(RecordOther data);

    /**
     * 删除其他
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_record_other where id=#{id}")
    int deleteById(int id);

    /**
     * 修改其他
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateOther")
    int updateOther(RecordOther data);

    /**
     * 查询其他简要信息
     *
     * @param userId 用户id
     * @return List<RecordOther>
     */
    @Select("select * from tb_record_other where user=#{userId} order by add_time desc limit 1")
    List<RecordOther> listBriefOther(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordOther
     */
    @Select("select * from tb_record_other where id=#{id}")
    RecordOther selectById(int id);
}
