package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.model.bean.send.Send;
import cn.globalyouth.cosineapi.model.vo.req.SendStateReqVo;

import java.util.List;

import org.apache.ibatis.annotations.*;

/**
 * @author liuyufeng
 * tb_send 投递dao
 */
@Mapper
public interface SendDao {
  /**
   * 添加投递
   *
   * @param data 投递对象
   * @return 0/1
   */
  @Insert(
    "insert into tb_send(user,resume,resume_title,company,job,state,add_time,date1) values " +
    "(#{user.id},#{resume.id},#{resumeTitle},#{company.id},#{job.id},#{state},#{addTime},#{date1})"
  )
  int addSend(Send data);

  /**
   * 添加官网投递
   *
   * @param data 投递对象
   * @return 0/1
   */
  @Insert(
    "insert into tb_send(user,company,job,state,add_time,date1) values " +
    "(#{user.id},#{company.id},#{job.id},#{state},#{addTime},#{date1})"
  )
  int addWeb(Send data);

  /**
   * 查询过去某天未处理的简历
   *
   * @param dayBefore 距离今天的天数
   * @return List<Send>
   */
  @Select(
    "select * from tb_send where state=0 and DATEDIFF(add_time, now())=#{dayBefore} order by company,job,add_time"
  )
  @Results(
    id = "sendMapper",
    value = {
      @Result(
        column = "user",
        property = "user",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.user.UserDao.selectBriefById"
        )
      ),
      @Result(
        column = "resume",
        property = "resume",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.resume.ResumeDao.selectBriefById"
        )
      ),
      @Result(
        column = "company",
        property = "company",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectBriefById"
        )
      ),
      @Result(
        column = "job",
        property = "job",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectBriefById"
        )
      ),
    }
  )
  List<Send> listSendByDayBefore(int dayBefore);

  /**
   * 查询过去20次投递的数据 用于分析
   *
   * @param userId 用户id
   * @return List<Send>
   */
  @Select(
    "select * from tb_send where user=#{userId} order by add_time desc limit 10"
  )
  @Results(
    id = "sendMapper2",
    value = {
      @Result(
        column = "user",
        property = "user",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.user.UserDao.selectBriefById"
        )
      ),
      @Result(
        column = "resume",
        property = "resume",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.resume.ResumeDao.selectBriefById"
        )
      ),
      @Result(
        column = "company",
        property = "company",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectAnalyticById"
        )
      ),
      @Result(
        column = "job",
        property = "job",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectAnalyticById"
        )
      ),
    }
  )
  List<Send> listSendHistory(int userId);

  /**
   * 统计是否已投递
   * @param userId 用户id
   * @param jobId 职位id
   * @return 0/1
   */
  @Select("select count(*) from tb_send where user=#{userId} and job=#{jobId}")
  int countSend(@Param("userId") int userId, @Param("jobId") int jobId);

  /**
   * 更新投递进度
   * @param reqVo 更新投递进度
   * @return 0/1
   */
  @Update("update tb_send set state=#{state},date2=#{date2},date3=#{date3}," +
  "date4=#{date4} where user=#{userId} and job=#{jobId}")
  int updateSend(SendStateReqVo reqVo);

  /**
   * 列举投递进度
   * @param userId 用户id
   * @param sortType 排序
   * @return List<Send>
   */
  @Select(
    "select * from tb_send where user=#{userId} order by add_time desc"
  )
  @Results(
    id = "sendMapper3",
    value = {
      @Result(
        column = "user",
        property = "user",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.user.UserDao.selectBriefById"
        )
      ),
      @Result(
        column = "resume",
        property = "resume",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.resume.ResumeDao.selectBriefById"
        )
      ),
      @Result(
        column = "company",
        property = "company",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectAnalyticById"
        )
      ),
      @Result(
        column = "job",
        property = "job",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectAnalyticById"
        )
      ),
    }
  )
  List<Send> listSend(@Param("userId") int userId, @Param("sortType") int sortType);

  /**
   * 查询投递进度
   * @param userId 用户id
   * @param sortType 排序
   * @param name 模糊查询
   * @return List<Send>
   */
  @SelectProvider(type = SendSql.class, method = "selectLike")
  @Results(
    id = "sendMapper4",
    value = {
      @Result(
        column = "user",
        property = "user",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.user.UserDao.selectBriefById"
        )
      ),
      @Result(
        column = "resume",
        property = "resume",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.resume.ResumeDao.selectBriefById"
        )
      ),
      @Result(
        column = "company",
        property = "company",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectAnalyticById"
        )
      ),
      @Result(
        column = "job",
        property = "job",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectAnalyticById"
        )
      ),
    }
  )
  List<Send> searchSend(@Param("userId") int userId, @Param("sortType") int sortType, @Param("name") String name);
}
