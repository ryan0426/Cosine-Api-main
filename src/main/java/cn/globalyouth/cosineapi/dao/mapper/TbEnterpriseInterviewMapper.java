package cn.globalyouth.cosineapi.dao.mapper;

import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInterview;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbEnterpriseInterviewMapper extends Mapper<TbEnterpriseInterview> {
    int updateBatch(List<TbEnterpriseInterview> list);

    int updateBatchSelective(List<TbEnterpriseInterview> list);

    int batchInsert(@Param("list") List<TbEnterpriseInterview> list);

    int insertOrUpdate(TbEnterpriseInterview record);

    int insertOrUpdateSelective(TbEnterpriseInterview record);
}