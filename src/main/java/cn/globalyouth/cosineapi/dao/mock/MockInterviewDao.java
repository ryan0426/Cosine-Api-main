package cn.globalyouth.cosineapi.dao.mock;

import cn.globalyouth.cosineapi.model.bean.mock.MockInterview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_mock_interview 模拟面试dao
 */
@Mapper
public interface MockInterviewDao {

    /**
     * 查询尚未开始的所有模面
     *
     * @return List<MockInterview>
     */
    @Select("select * from tb_mock_interview where time >= now() order by time limit 20")
    List<MockInterview> listAll();

    /**
     * 查询特定行业模面
     *
     * @param industryId 行业id
     * @return List<MockInterview>
     */
    @Select("select * from tb_mock_interview where industry=#{industryId} " +
            "and time >= now() order by time limit 20")
    List<MockInterview> listByIndustry(int industryId);

    /**
     * 查询时间段内的模面
     *
     * @param fromTime 开始时间
     * @param toTime   结束时间
     * @return List<MockInterview>
     */
    @Select("select * from tb_mock_interview where time between #{fromTime} and #{toTime} " +
            "and time >= now() order by time")
    List<MockInterview> listByTime(Date fromTime, Date toTime);

    /**
     * 按行业和时间段查询
     *
     * @param industryId 行业id
     * @param fromTime   开始时间
     * @param toTime     结束时间
     * @return List<MockInterview>
     */
    @Select("select * from tb_mock_interview where time between #{fromTime} and #{toTime} " +
            "and industry=#{industryId} and time >= now() order by time")
    List<MockInterview> listByIndustryAndTime(int industryId, Date fromTime, Date toTime);

    /**
     * 根据id查询
     *
     * @param id 数据id
     * @return MockInterview
     */
    @Select("select * from tb_mock_interview where id=#{id}")
    MockInterview selectById(int id);

}
