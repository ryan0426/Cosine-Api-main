package cn.globalyouth.cosineapi.dao.type;

import cn.globalyouth.cosineapi.model.bean.type.School;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author liuyufeng
 * tb_school 学校dao
 */
@Mapper
public interface SchoolDao {

    /**
     * 添加学校
     *
     * @param school 学校对象
     * @return 0/1
     */
    @Insert("insert into tb_school values (#{id},#{name},#{code},#{major},#{location},#{layer},#{comment})")
    int addSchool(School school);

    /**
     * 学校全部清空
     *
     * @return 删除数量
     */
    @Delete("delete from tb_school")
    int deleteAll();

    /**
     * 模糊查询学校
     *
     * @param name 模糊查询匹配值
     * @return List<String>
     */
    @SelectProvider(type = SchoolSql.class, method = "selectLike")
    List<String> listSchool(String name);
}
