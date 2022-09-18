package cn.globalyouth.cosineapi.dao.resume;

import cn.globalyouth.cosineapi.model.bean.resume.ResumeCase;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuyufeng
 * tb_resume_case 简历案例dao
 */
@Mapper
public interface ResumeCaseDao {
  /**
   * 列出最热门的5份案例
   *
   * @return List<ResumeCase>
   */
  @Select(
    "select id,title,introduction,tags from tb_resume_case order by view desc limit 5"
  )
  List<ResumeCase> listHot();

  /**
   * 列出特定行业的案例
   *
   * @param industryId 行业id
   * @return List<ResumeCase>
   */
  @Select(
    "select id,title,introduction,tags from tb_resume_case where industry=#{industryId} order by view desc"
  )
  List<ResumeCase> listByIndustry(int industryId);

  /**
   * 按id查询案例详情
   *
   * @param id 数据id
   * @return ResumeCase
   */
  @Select("select * from tb_resume_case where id=#{id}")
  ResumeCase selectById(int id);
}
