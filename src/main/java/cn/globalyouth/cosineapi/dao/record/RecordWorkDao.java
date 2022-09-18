package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordWork;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng
 * tb_record_work 履历 工作经历dao
 */
@Mapper
public interface RecordWorkDao {

    /**
     * 添加工作经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_work(user,company,position,department,city,from_date,to_date,description,add_time) " +
            "values (#{user},#{company},#{position},#{department},#{city},#{fromDate},#{toDate},#{description},#{addTime})")
    int addWork(RecordWork data);

    /**
     * 删除工作经历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_record_work where id=#{id}")
    int deleteById(int id);

    /**
     * 更新工作经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateWork")
    int updateWork(RecordWork data);

    /**
     * 查询工作经历简要信息
     *
     * @param userId 用户id
     * @return List<RecordWork>
     */
    @Select("select id,company,position,from_date,to_date from tb_record_work where user=#{userId} order by from_date")
    List<RecordWork> listBriefWork(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordWork
     */
    @Select("select * from tb_record_work where id=#{id}")
    RecordWork selectById(int id);
}
