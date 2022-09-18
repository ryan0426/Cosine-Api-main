package cn.globalyouth.cosineapi.service.enterprise;

import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInfo;

/**
 * 企业信息的service
 */
public interface EnterPriseInfoService {

    /**
     * 根据企业名查询企业信息
     * @param name
     * @return
     */
    TbEnterpriseInfo queryByName(String name);


    /**
     * 根据id查询企业信息
     * @param id
     * @return
     */
    TbEnterpriseInfo queryById(String id);

}
