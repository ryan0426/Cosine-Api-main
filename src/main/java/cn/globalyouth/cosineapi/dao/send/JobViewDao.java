package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.model.bean.send.JobView;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng
 * tb_job_view 查看职位dao
 */
@Mapper
public interface JobViewDao {

    /**
     * 添加查看职位信息
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_job_view(user,company,job,add_time) values " +
            "(#{user.id},#{company.id},#{job.id},#{addTime})")
    int addView(JobView data);

    /**
     * 查询过去20次浏览的数据 用于分析
     *
     * @param userId 用户id
     * @return List<JobView>
     */
    @Select("select * from tb_job_view where user=#{userId} order by add_time desc limit 10")
    @Results(id = "jobViewMapper", value = {
            @Result(column = "user", property = "user", one = @One(select = "cn.globalyouth.cosineapi.dao.user.UserDao.selectBriefById")),
            @Result(column = "company", property = "company", one = @One(select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectAnalyticById")),
            @Result(column = "job", property = "job", one = @One(select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectAnalyticById"))
    })
    List<JobView> listJobViewHistory(int userId);
}
