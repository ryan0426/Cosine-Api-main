package cn.globalyouth.cosineapi.dao.record;

import cn.globalyouth.cosineapi.model.bean.record.RecordEducation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * tb_record_education 履历 教育经历dao
 */
@Mapper
public interface RecordEducationDao {

    /**
     * 添加教育经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_record_education(user,school,education,major,minor,college,from_date,to_date,description,add_time) " +
            "values (#{user},#{school},#{education},#{major},#{minor},#{college},#{fromDate},#{toDate},#{description},#{addTime})")
    int addEducation(RecordEducation data);

    /**
     * 删除教育经历
     *
     * @param id 数据id
     * @return 0/1
     */
    @Delete("delete from tb_record_education where id=#{id}")
    int deleteById(int id);

    /**
     * 修改教育经历
     *
     * @param data 数据对象
     * @return 0/1
     */
    @UpdateProvider(type = RecordSql.class, method = "updateEducation")
    int updateEducation(RecordEducation data);

    /**
     * 查询教育经历简要信息
     *
     * @param userId 用户id
     * @return List<RecordEducation>
     */
    @Select("select id,school,education,from_date,to_date from tb_record_education where user=#{userId} order by from_date")
    List<RecordEducation> listBriefEducation(int userId);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return RecordEducation
     */
    @Select("select * from tb_record_education where id=#{id}")
    RecordEducation selectById(int id);
}
