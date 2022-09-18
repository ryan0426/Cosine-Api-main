package cn.globalyouth.cosineapi.dao.resume;

import cn.globalyouth.cosineapi.model.bean.resume.Resume;
import cn.globalyouth.cosineapi.model.dto.resume.VideoResumeDto;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng
 * tb_resume 简历dao
 */
@Mapper
public interface ResumeDao {

    /**
     * 列出用户简历
     *
     * @param userId 用户id
     * @return List<Resume>
     */
    @Select("select * from tb_resume where user=#{userId} order by add_time desc")
    List<Resume> listResume(int userId);

    /**
     * 查询用户第一份简历
     *
     * @param userId 用户id
     * @return Resume
     */
    @Select("select id,title,file_path,is_default,grade,percent,language,add_time from tb_resume where " +
            "user=#{userId} order by is_default desc, add_time desc limit 1")
    Resume selectTop(int userId);

    /**
     * 按id查询简历
     *
     * @param id 数据id
     * @return Resume
     */
    @Select("select id,title,file_path,is_default,grade,percent,language,add_time from tb_resume where " +
            "id=#{id}")
    Resume selectById(int id);

    /**
     * 按id查询简历
     *
     * @param id 数据id
     * @return Resume
     */
    @Select("select id,title,resumeKey from tb_resume where id=#{id}")
    Resume selectBriefById(int id);

    /**
     * 统计用户简历
     *
     * @param userId 用户id
     * @return 数量
     */
    @Select("select count(*) from tb_resume where user=#{userId}")
    int countResume(int userId);

    /**
     * 设置为默认简历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Update("update tb_resume set is_default=1 where id=#{id}")
    int setDefault(int id);

    /**
     * 设置所有简历为非默认
     *
     * @param userId 用户id
     * @return 改变数据数量
     */
    @Update("update tb_resume set is_default=0 where user=#{userId}")
    int setNotDefault(int userId);

    /**
     * 修改名称
     *
     * @param id    数据id
     * @param title 名称
     * @return 0/1
     */
    @Update("update tb_resume set title=#{title} where id=#{id}")
    int updateTitle(@Param("id") int id, @Param("title") String title);

    /**
     * 添加简历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_resume(user, title, record_values, resumeKey, is_default, add_time) values " +
            "(#{user}, #{title}, #{recordValues}, #{resumeKey}, #{isDefault}, #{addTime})")
    int addResume(Resume data);

    /**
     * "删除"简历，将user置为-1
     *
     * @param id 数据id
     * @return 0/1
     */
    @Update("update tb_resume set user=-1 where id=#{id}")
    int deleteResume(int id);

    /**
     * 添加视频简历
     *
     * @param userId 用户id
     * @param key
     * @return 0/1
     */
    @Insert(
    "insert into tb_videoresume(userId,resumeKey) " +
    "values (#{userId},#{key})"
  )
    int addVideoResume(@Param("userId") int userId, @Param("key") String key);

    /**
     * 删除视频简历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_videoresume where id=#{id}")
    int deleteVideoResume(int id);

    /**
     * 按id查询视频简历
     *
     * @param id 用户id
     * @return 
     */
    @Select("select id, resumeKey from tb_videoresume where userId=#{id}")
    List<VideoResumeDto> videoById(int id);

}
