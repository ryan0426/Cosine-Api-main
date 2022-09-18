package cn.globalyouth.cosineapi.dao.type;

import cn.globalyouth.cosineapi.model.bean.type.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_industry 行业dao
 */
@Mapper
public interface IndustryDao {

    /**
     * 查询所有mock行业
     *
     * @return List<Industry>
     */
    @Select("select id,name from tb_industry where sort>1000 order by sort")
    List<Industry> listMock();

    /**
     * 查询所有学院行业
     *
     * @return List<Industry>
     */
    @Select("select id,name from tb_industry where sort2>2000 order by sort2")
    List<Industry> listCollege();

    /**
     * 查询所有行业
     *
     * @return List<Industry>
     */
    @Select("select id,name from tb_industry where sort>-1 order by sort desc")
    List<Industry> listAll();

    /**
     * 根据id查询对应行业名
     *
     * @param id 行业id
     * @return name
     */
    @Select("select name from tb_industry where id=#{id}")
    String selectById(int id);
}
