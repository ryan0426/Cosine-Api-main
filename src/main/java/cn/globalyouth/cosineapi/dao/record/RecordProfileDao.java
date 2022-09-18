package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordProfile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuyufeng
 * tb_record_profile 履历 头像dao
 */
@Mapper
public interface RecordProfileDao {

    /**
     * 添加履历头像
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_profile(user,profile,add_time) values (#{user},#{profile},#{addTime})")
    int addProfile(RecordProfile data);

    /**
     * 查询最新头像
     *
     * @param userId 用户id
     * @return 头像url
     */
    @Select("select profile from tb_record_profile where user=#{userId} order by add_time desc limit 1")
    String selectLatestProfile(int userId);
}
