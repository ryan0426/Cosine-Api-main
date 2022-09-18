package cn.globalyouth.cosineapi.dao.mapper;

import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseOffice;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbEnterpriseOfficeMapper extends Mapper<TbEnterpriseOffice> {
    int updateBatch(List<TbEnterpriseOffice> list);

    int updateBatchSelective(List<TbEnterpriseOffice> list);

    int batchInsert(@Param("list") List<TbEnterpriseOffice> list);

    int insertOrUpdate(TbEnterpriseOffice record);

    int insertOrUpdateSelective(TbEnterpriseOffice record);
}