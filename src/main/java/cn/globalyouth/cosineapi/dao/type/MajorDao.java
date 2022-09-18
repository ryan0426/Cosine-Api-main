package cn.globalyouth.cosineapi.dao.type;

import cn.globalyouth.cosineapi.model.bean.type.Major;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_major 大学专业dao
 */
@Mapper
public interface MajorDao {

    /**
     * 添加专业
     *
     * @param major 专业
     * @return 0/1
     */
    @Insert("insert into tb_major values (#{id},#{name},#{code})")
    int addMajor(Major major);

    /**
     * 删除所有专业
     *
     * @return 删除数量
     */
    @Delete("delete from tb_major")
    int deleteAll();

    /**
     * 模糊查询专业
     *
     * @param name 专业名
     * @return List<String>
     */
    @Select("select name from tb_major where length(code)>4 and name like #{name} limit 5")
    List<String> listMajor(String name);
}
