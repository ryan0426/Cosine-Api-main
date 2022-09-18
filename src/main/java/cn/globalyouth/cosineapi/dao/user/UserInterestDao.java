package cn.globalyouth.cosineapi.dao.user;

import cn.globalyouth.cosineapi.model.bean.user.UserInterest;
import java.sql.Timestamp;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * tb_user_interest 关注dao
 */
@Mapper
public interface UserInterestDao {
  /**
   * 添加关注
   *
   * @param userId 用户id
   * @param id 职位id
   * @param addTime 添加关注时间戳
   * @return 0/1
   */
  @Insert(
    "insert into tb_user_interested_job(user_id, job_id, subscribe_time) " +
    "value (#{user_id}, #{job_id}, #{subscribe_time})"
  )
  int addInterest(
    @Param("user_id") int userId,
    @Param("job_id") int id,
    @Param("subscribe_time") Timestamp addTime
  );

  /**
   * 取消关注
   *
   * @param userId 用户id
   * @param id 职位id
   * @return 0/1
   */
  @Delete("delete from tb_user_interested_job where user_id=#{user_id} and job_id=#{job_id}")
  int deleteInterest(
    @Param("user_id") int userId, 
    @Param("job_id") int id
    ); 



  /**
   * 获取关注列表
   *
   * @param userId 用户id
   * @param id 职位id
   * @return UserInterest
   */
  @Select("select * from tb_user_interested_job where user_id=#{user_id} order by subscribe_time desc ")
  @Results(
    id = "interestMapper",
    value = {
      @Result(
        property = "job",
        column = "job_id",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.JobDao.selectById"
        )
      ),
    }
  )
  List<UserInterest> displayInterest(
    @Param("user_id") int userId,
    @Param("sortType") int sortType
  );


  /**
   * 查询是否关注
   *
   * @param userId 用户id
   * @param id 职位id
   * @return 0/1
   */
  @Select("select count(*) from tb_user_interested_job where user_id=#{user_id} and job_id=#{job_id}")
  int checkInterest(
    @Param("user_id") int userId, 
    @Param("job_id") int id
    );
}
