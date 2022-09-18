package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordActivity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng
 * tb_record_activity 履历 组织活动经历dao
 */
@Mapper
public interface RecordActivityDao {

    /**
     * 添加组织/活动经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_activity(user,activity,role,department,city,from_date,to_date,description,add_time) " +
            "values (#{user},#{activity},#{role},#{department},#{city},#{fromDate},#{toDate},#{description},#{addTime})")
    int addActivity(RecordActivity data);

    /**
     * 删除组织/活动经历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_record_activity where id=#{id}")
    int deleteById(int id);

    /**
     * 更新组织/活动经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateActivity")
    int updateActivity(RecordActivity data);

    /**
     * 查询组织/活动经历简要信息
     *
     * @param userId 用户id
     * @return List<RecordActivity>
     */
    @Select("select id,activity,role,from_date,to_date from tb_record_activity where user=#{userId} order by from_date")
    List<RecordActivity> listBriefActivities(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordActivity
     */
    @Select("select * from tb_record_activity where id=#{id}")
    RecordActivity selectById(int id);
}
