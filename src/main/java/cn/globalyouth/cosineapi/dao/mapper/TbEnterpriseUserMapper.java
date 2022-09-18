package cn.globalyouth.cosineapi.dao.mapper;

import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbEnterpriseUserMapper extends Mapper<TbEnterpriseUser> {
    int updateBatch(List<TbEnterpriseUser> list);

    int updateBatchSelective(List<TbEnterpriseUser> list);

    int batchInsert(@Param("list") List<TbEnterpriseUser> list);

    int insertOrUpdate(TbEnterpriseUser record);

    int insertOrUpdateSelective(TbEnterpriseUser record);
}