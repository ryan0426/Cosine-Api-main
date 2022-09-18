package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordAward;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng
 * tb_record_award 履历 获奖经历dao
 */
@Mapper
public interface RecordAwardDao {

    /**
     * 添加获奖经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_award(user,award,level,date,description,add_time) " +
            "values (#{user},#{award},#{level},#{date},#{description},#{addTime})")
    int addAward(RecordAward data);

    /**
     * 删除获奖经历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_record_award where id=#{id}")
    int deleteById(int id);

    /**
     * 更新获奖经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateAward")
    int updateAward(RecordAward data);

    /**
     * 查询获奖经历简要信息
     *
     * @param userId 用户id
     * @return List<RecordActivity>
     */
    @Select("select id,award,date from tb_record_award where user=#{userId} order by date")
    List<RecordAward> listBriefAwards(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordActivity
     */
    @Select("select * from tb_record_award where id=#{id}")
    RecordAward selectById(int id);
}
