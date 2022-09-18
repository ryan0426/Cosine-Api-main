package cn.globalyouth.cosineapi.dao.mapper;

import cn.globalyouth.cosineapi.model.pojo.TbResume;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbResumeMapper extends Mapper<TbResume> {
    int updateBatch(List<TbResume> list);

    int batchInsert(@Param("list") List<TbResume> list);

    int insertOrUpdate(TbResume record);

    int insertOrUpdateSelective(TbResume record);
}