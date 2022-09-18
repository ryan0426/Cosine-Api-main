package cn.globalyouth.cosineapi.dao.user;

import cn.globalyouth.cosineapi.model.bean.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_user 用户dao
 */
@Mapper
public interface UserDao {

    /**
     * 添加用户 微信登录
     *
     * @param user 用户
     * @return 0/1
     */
    @Insert("insert into tb_user(openid,name,profile,gender,add_time,login_time) values " +
            "(#{openid},#{name},#{profile},#{gender},now(),now())")
    int addByWechat(User user);

    /**
     * 添加用户 手机号登录
     *
     * @param user 用户
     * @return 0/1
     */
    @Insert("insert into tb_user(openid,phone,add_time,login_time) values " +
            "(#{openid},#{phone},now(),now())")
    int addByPhone(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 0/1
     */
    @UpdateProvider(type = UserSql.class, method = "update")
    int update(User user);

    /**
     * 按id查询用户
     *
     * @param id 用户id
     * @return User
     */
    @Select("select * from tb_user where id=#{id}")
    User selectById(int id);

    /**
     * 按id查询用户
     *
     * @param id 用户id
     * @return User
     */
    @Select("select id,name,phone from tb_user where id=#{id}")
    User selectBriefById(int id);

    /**
     * 按openid查询用户
     *
     * @param openid 微信openid
     * @return User
     */
    @Select("select * from tb_user where openid=#{openid}")
    User selectByOpenid(String openid);

    /**
     * 查询模面发起人
     *
     * @param id 用户id
     * @return User
     */
    @Select("select id, name, profile, introduction from tb_user where id=#{id}")
    User selectInitiator(int id);

    /**
     * 查询模面参与者
     *
     * @param ids 用户id列表
     * @return List<User>
     */
    @SelectProvider(type = UserSql.class, method = "selectUsers")
    List<User> selectParticipant(List<Integer> ids);

    /**
     * 校验用户
     *
     * @param id     用户id
     * @param openid 用户openid
     * @return 0/1
     */
    @Select("select count(*) from tb_user where id=#{id} and openid=#{openid}")
    int checkByWechat(int id, String openid);

    /**
     * 列出所有用户
     *
     * @return List<String>
     */
    @Select("SELECT * FROM tb_user WHERE openid IS NOT NULL AND phone IS NOT NULL")
    List<String> listAllOpenid();
}
