package cn.globalyouth.cosineapi.service.enterprise.impl;

import cn.globalyouth.cosineapi.dao.mapper.TbEnterpriseInfoMapper;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInfo;
import cn.globalyouth.cosineapi.service.enterprise.EnterPriseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnterPriseInfoServiceImpl implements EnterPriseInfoService {
    @Autowired
    private TbEnterpriseInfoMapper infoMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbEnterpriseInfo queryByName(String name) {
        TbEnterpriseInfo info = new TbEnterpriseInfo();
        info.setName(name);
        return infoMapper.selectOne(info);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbEnterpriseInfo queryById(String id) {
       return infoMapper.selectByPrimaryKey(id);
    }
}
