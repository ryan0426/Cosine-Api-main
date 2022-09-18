package cn.globalyouth.cosineapi.service.enterprise.impl;

import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.exception.GraceException;
import cn.globalyouth.cosineapi.common.utils.org.n3r.idworker.Sid;
import cn.globalyouth.cosineapi.dao.mapper.TbEnterpriseInfoMapper;
import cn.globalyouth.cosineapi.dao.mapper.TbEnterpriseUserMapper;
import cn.globalyouth.cosineapi.model.bo.enterprise.user.UserRegisterBO;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseInfo;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseUser;
import cn.globalyouth.cosineapi.model.vo.enterprise.UserVO;
import cn.globalyouth.cosineapi.service.enterprise.BaseService;
import cn.globalyouth.cosineapi.service.enterprise.EnterPriseUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EnterPriseUserServiceImpl extends BaseService implements EnterPriseUserService {
    @Autowired
    private Sid sid;

    @Autowired
    private TbEnterpriseUserMapper userMapper;

    @Autowired
    private TbEnterpriseInfoMapper infoMapper;


    /**
     * 企业用户注册
     *
     * @param userRegisterBO
     * @return
     */
    @Override
    @Transactional
    public UserVO createUser(UserRegisterBO userRegisterBO) {
        String id = sid.nextShort();
        TbEnterpriseInfo enterpriseInfo = new TbEnterpriseInfo();
        BeanUtils.copyProperties(userRegisterBO, enterpriseInfo);
        enterpriseInfo.setId(id);
        enterpriseInfo.setCreateTime(new Date());
        enterpriseInfo.setUpdateTime(new Date());
        int infoRes = infoMapper.insertOrUpdate(enterpriseInfo);
        if (infoRes != 1) {
            GraceException.display(ResponseEnumState.INSERT_FAILED);
        }
        TbEnterpriseUser user = new TbEnterpriseUser();
        BeanUtils.copyProperties(userRegisterBO, user);
        user.setEnterpriseId(id);
        id = sid.nextShort();
        user.setId(id);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int userRes = userMapper.insert(user);
        if (userRes != 1) {
            GraceException.display(ResponseEnumState.INSERT_FAILED);
        }
        return null;
    }

    @Override
    @Transactional
    public TbEnterpriseUser createUser(UserRegisterBO userRegisterBO,String enterpriseId){
        TbEnterpriseUser user = new TbEnterpriseUser();
        BeanUtils.copyProperties(userRegisterBO,user);
        user.setId(sid.nextShort());
        user.setEnterpriseId(enterpriseId);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int result = userMapper.insert(user);
        if (result != 1) {
            GraceException.display(ResponseEnumState.INSERT_FAILED);
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbEnterpriseUser queryByUsername(String username) {
        TbEnterpriseUser user = new TbEnterpriseUser();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbEnterpriseUser queryByEmail(String email) {
        TbEnterpriseUser user = new TbEnterpriseUser();
        user.setEmail(email);
        return userMapper.selectOne(user);
    }


}
