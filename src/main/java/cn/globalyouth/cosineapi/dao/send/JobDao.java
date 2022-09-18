package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.model.bean.send.Job;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author liuyufeng
 * tb_job 职位dao
 */
@Mapper
public interface JobDao {
  /**
   * 添加职位
   *
   * @param job 数据对象
   * @return 0/1
   */
  @Insert(
    "insert into tb_job values (#{id},#{title},#{minSalary},#{maxSalary},#{salary},#{location}," +
    "#{education},#{nature},#{isEager},#{canOnline},#{deadline},#{addTime},#{description},#{demand}," +
    "#{address},#{show},#{companyId},0,0)"
  )
  int addJob(Job job);

  /**
   * 统计职位总数
   *
   * @return 数量
   */
  @Select("select id from tb_job order by id desc limit 1")
  int countJob();

  /**
   * 按id查询职位
   *
   * @param id 数据id
   * @return Job
   */
  @Select("select * from tb_job where id=#{id}")
  @Results(
    id = "jobMapper",
    value = {
      @Result(
        property = "company",
        column = "company_id",
        one = @One(
          select = "cn.globalyouth.cosineapi.dao.send.CompanyDao.selectById"
        )
      ),
    }
  )
  Job selectById(int id);

  /**
   * 按id查询职位
   *
   * @param id 数据id
   * @return Job
   */
  @Select("select id,title,location from tb_job where id=#{id}")
  Job selectBriefById(int id);

  /**
   * 按id查询职位
   *
   * @param id 数据id
   * @return Job
   */
  @Select("select id,title,salary,location,nature from tb_job where id=#{id}")
  Job selectAnalyticById(int id);

  /**
   * 按id查询职位
   *
   * @param ids 数据id
   * @return List<Job>
   */
  @Select("select id,title,company_id from tb_job where id in ${ids}")
  @ResultMap("jobMapper")
  List<Job> listByIds(String ids);

  /**
   * 按投递数排序列出职位
   *
   * @param offset 偏移量
   * @param count  数量
   * @return List<Job>
   */
  @Select(
    "select * from tb_job order by send_cnt desc, view_cnt desc limit #{offset}, #{count}"
  )
  @ResultMap("jobMapper")
  List<Job> listBySend(@Param("offset") int offset, 
  @Param("count") int count);

  /**
   * 按浏览数排序列出职位
   *
   * @param offset 偏移量
   * @param count  数量
   * @return List<Job>
   */
  @Select(
    "select * from tb_job order by view_cnt desc, send_cnt desc limit #{offset}, #{count}"
  )
  @ResultMap("jobMapper")
  List<Job> listByView(
    @Param("offset") int offset, 
    @Param("count") int count);

  /**
   * 按发布时间排序列出职位
   *
   * @param offset 偏移量
   * @param count  数量
   * @return List<Job>
   */
  @Select(
    "select * from tb_job order by add_time desc limit #{offset}, #{count}"
  )
  @ResultMap("jobMapper")
  List<Job> listByTime(
    @Param("offset") int offset, 
    @Param("count") int count);

  /**
   * 按名称模糊查询职位
   *
   * @param name   名称查询值
   * @param offset 偏移量
   * @param count  数量
   * @return List<Job>
   */
  @Select(
    "select j.* from tb_job j join tb_company c on j.company_id=c.id " +
    "where j.title like #{name} or c.name like #{name} order by add_time desc limit #{offset}, #{count}"
  )
  @ResultMap("jobMapper")
  List<Job> listByName(
    @Param("name") String name, 
    @Param("offset") int offset, 
    @Param("count") int count);

  /**
   * 按条件查询职位
   *
   * @param locations      城市
   * @param industries     行业
   * @param jobNatures     工作性质
   * @param educations     学历
   * @param salaries       月薪
   * @param companyNatures 公司性质
   * @param name           名称
   * @param haiTou         是否一键海投
   * @param offset         偏移量
   * @param count          数量
   * @return List<Job>
   */
  @SelectProvider(type = JobSql.class, method = "listByConditions")
  @ResultMap("jobMapper")
  List<Job> listByConditions(
    @Param("locations") List<String> locations,
    @Param("industries") List<String> industries,
    @Param("jobNatures") List<String> jobNatures,
    @Param("educations") List<String> educations,
    @Param("salaries") Integer salaries,
    @Param("companyNatures") List<String> companyNatures,
    @Param("name") String name,
    @Param("haiTou") boolean haiTou,
    @Param("offset") int offset,
    @Param("count") int count
  );

  /**
   * 按条件筛选岗位
   *
   * @param locations  城市
   * @param industries 行业
   * @param jobNatures 工作性质
   * @param count      数量
   * @return List<Job>
   */
  @SelectProvider(type = JobSql.class, method = "listByConditions2")
  @ResultMap("jobMapper")
  List<Job> listByConditions2(
    @Param("locations") List<String> locations,
    @Param("industries") List<String> industries,
    @Param("jobNatures") List<String> jobNatures,
    @Param("count") int count
  );

  /**
   * 按公司查询
   *
   * @param companyId 公司id
   * @param name      名称
   * @param offset    偏移量
   * @param count     数量
   * @return List<Job>
   */
  @Select(
    "select * from tb_job where company_id=#{companyId} and title like #{name} " +
    "order by send_cnt desc, add_time desc limit #{offset}, #{count}"
  )
  @ResultMap("jobMapper")
  List<Job> listByCompany(
    @Param("companyId") int companyId, 
    @Param("name") String name, 
    @Param("offset") int offset, 
    @Param("count") int count);

  /**
   * 浏览量+1
   *
   * @param id 数据id
   * @return 0/1
   */
  @Update("update tb_job set view_cnt=(view_cnt+1) where id=#{id}")
  int incViewCnt(int id);

  /**
   * 投递量+1
   *
   * @param id 数据id
   * @return 0/1
   */
  @Update("update tb_job set send_cnt=(send_cnt+1) where id=#{id}")
  int incSendCnt(int id);

  /**
   * 编辑职位为官网投递
   *
   * @param id      职位id
   * @param website 官网地址
   * @return 0/1
   */
  @Update("update tb_job set can_online=#{website} where id=#{id}")
  int updateWebsiteSendJob(
    @Param("id") int id, 
    @Param("website") String website);
}
