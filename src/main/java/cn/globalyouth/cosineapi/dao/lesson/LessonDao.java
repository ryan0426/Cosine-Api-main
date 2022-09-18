package cn.globalyouth.cosineapi.dao.lesson;

import cn.globalyouth.cosineapi.model.bean.lesson.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_lesson 课程dao
 */
@Mapper
public interface LessonDao {

    /**
     * 统计课程数量
     *
     * @return 数量
     */
    @Select("select count(*) from tb_lesson")
    int countLessons();

    /**
     * 根据类型查询课程
     *
     * @param typeId 类型id
     * @return List<Lesson>
     */
    @Select("select id,name,introduction,count,image from tb_lesson where type=#{typeId} order by count desc")
    List<Lesson> listByType(int typeId);

    /**
     * 根据名称查询课程
     *
     * @param name 名称
     * @return List<Lesson>
     */
    @Select("select id,name,introduction,count,image from tb_lesson where name like #{name} order by count desc")
    List<Lesson> listByName(String name);
}
