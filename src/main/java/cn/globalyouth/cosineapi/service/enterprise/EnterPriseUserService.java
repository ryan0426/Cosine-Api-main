package cn.globalyouth.cosineapi.service.enterprise;

import cn.globalyouth.cosineapi.model.bo.enterprise.user.UserRegisterBO;
import cn.globalyouth.cosineapi.model.pojo.TbEnterpriseUser;
import cn.globalyouth.cosineapi.model.vo.enterprise.UserVO;

/**
 * 企业用户的service
 */

public interface EnterPriseUserService {
    /**
     * 用户注册
     * @param userRegisterBO
     * @return
     */
    UserVO createUser(UserRegisterBO userRegisterBO);


    /**
     * 创建用户 企业已经存在
     * @param
     * @return
     */
    TbEnterpriseUser createUser(UserRegisterBO userRegisterBO,String enterpriseId);


    /**
     *  根据用户名查询
     * @param username
     * @return
     */
    TbEnterpriseUser queryByUsername(String username);


    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    TbEnterpriseUser queryByEmail(String email);


}
