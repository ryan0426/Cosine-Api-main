package cn.globalyouth.cosineapi.dao.type;

import cn.globalyouth.cosineapi.model.bean.type.Position;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_position 职位dao
 */
@Mapper
public interface PositionDao {

    /**
     * 添加职位
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_position(name,code) values (#{name},#{code})")
    int addPosition(Position data);

    /**
     * 清空职位表
     *
     * @return 删除数量
     */
    @Delete("delete from tb_position")
    int deleteAll();

    /**
     * 列出职位类型
     *
     * @return List<Position>
     */
    @Select("select * from tb_position where length(code)=3")
    List<Position> listTypes();

    /**
     * 按类型查询职位
     *
     * @param typeCode 处理后的职位类型代码
     * @return List<Position>
     */
    @Select("select * from tb_position where code like #{typeCode}")
    List<Position> listByType(String typeCode);

    /**
     * 按id查询职位名
     *
     * @param id 职位id
     * @return String
     */
    @Select("select name from tb_position where id=#{id}")
    String selectById(int id);
}
