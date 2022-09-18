package cn.globalyouth.cosineapi.dao.summit;

import cn.globalyouth.cosineapi.model.bean.summit.VisaInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_visa_info 峰会Visa卡预留信息dao
 */
@Mapper
public interface VisaInfoDao {

    /**
     * 添加信息
     *
     * @param data 数据
     * @return 0/1
     */
    @Insert("insert into tb_visa_info(name,phone,location,add_time) values " +
            "(#{name},#{phone},#{location},#{addTime})")
    int addInfo(VisaInfo data);

    /**
     * 查询某天的预约
     *
     * @param dayBefore 距离今天的天数
     * @return List<VisaInfo>
     */
    @Select("select * from tb_visa_info where DATEDIFF(add_time, now())=#{dayBefore} order by add_time")
    List<VisaInfo> listByDayBefore(int dayBefore);
}
