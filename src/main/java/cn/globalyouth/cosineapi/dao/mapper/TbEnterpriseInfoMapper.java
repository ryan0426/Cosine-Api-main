package cn.globalyouth.cosineapi.dao.mapper;

import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbEnterpriseInfoMapper extends Mapper<TbEnterpriseInfo> {
    int updateBatch(List<TbEnterpriseInfo> list);

    int updateBatchSelective(List<TbEnterpriseInfo> list);

    int batchInsert(@Param("list") List<TbEnterpriseInfo> list);

    int insertOrUpdate(TbEnterpriseInfo record);

    int insertOrUpdateSelective(TbEnterpriseInfo record);
}