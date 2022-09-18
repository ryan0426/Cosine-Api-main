package cn.globalyouth.cosineapi.dao.user;

import cn.globalyouth.cosineapi.model.bean.user.UserIntention;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author liuyufeng
 * tb_user_intention 求职性质dao
 */
@Mapper
public interface UserIntentionDao {

    /**
     * 添加求职意向
     *
     * @param data 数据
     * @return 0/1
     */
    @Insert("insert into tb_user_intention(user,positions,industries,cities,intention_character,intern_length," +
            "arrival_time,add_time) values (#{user},#{positions},#{industries},#{cities},#{intentionCharacter}," +
            "#{internLength},#{arrivalTime},#{addTime})")
    int addIntention(UserIntention data);

    /**
     * 查询用户求职意向
     *
     * @param userId 用户id
     * @return UserIntention
     */
    @Select("select * from tb_user_intention where user=#{userId}")
    UserIntention selectByUser(int userId);

    /**
     * 修改求职意向
     *
     * @param data 数据
     * @return 0/1
     */
    @Update("update tb_user_intention set positions=#{positions},industries=#{industries},cities=#{cities}," +
            "intention_character=#{intentionCharacter},intern_length=#{internLength},arrival_time=#{arrivalTime}," +
            "add_time=#{addTime} where id=#{id}")
    int updateIntention(UserIntention data);
}
