package cn.globalyouth.cosineapi.dao.experience;

import cn.globalyouth.cosineapi.model.bean.experience.ExperienceHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_experience_history 面试经验搜索历史dao
 */
@Mapper
public interface ExperienceHistoryDao {

    /**
     * 按用户查询最近搜索历史 公司
     *
     * @param userId 用户id
     * @return List<ExperienceHistory>
     */
    @Select("select * from tb_experience_history where type=1 and user=#{userId} order by add_time desc, count desc limit 8")
    List<ExperienceHistory> listCompanyByUser(int userId);

    /**
     * 按热度查询搜索历史 公司
     *
     * @return List<ExperienceHistory>
     */
    @Select("select * from tb_experience_history where type=1 order by count desc, add_time desc limit 8")
    List<ExperienceHistory> listCompanyByHot();

    /**
     * 按用户查询最近搜索历史 岗位
     *
     * @param userId 用户id
     * @return List<ExperienceHistory>
     */
    @Select("select * from tb_experience_history where type=2 and user=#{userId} order by add_time desc, count desc limit 8")
    List<ExperienceHistory> listJobByUser(int userId);

    /**
     * 按热度查询搜索历史 公司
     *
     * @return List<ExperienceHistory>
     */
    @Select("select * from tb_experience_history where type=2 order by count desc, add_time desc limit 8")
    List<ExperienceHistory> listJobByHot();
}
