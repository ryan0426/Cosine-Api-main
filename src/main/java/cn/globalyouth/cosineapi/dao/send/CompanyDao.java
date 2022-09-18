package cn.globalyouth.cosineapi.dao.send;

import cn.globalyouth.cosineapi.model.bean.send.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liuyufeng
 * tb_company 公司dao
 */
@Mapper
public interface CompanyDao {

    /**
     * 添加公司
     *
     * @param data 数据对象
     * @return 0/1
     */
    @Insert("insert into tb_company values (#{id},#{name},#{logo},#{nature},#{industry},#{website},#{address}," +
            "#{description},#{welfare},#{contact},#{email},#{addTime},#{slogan})")
    int addCompany(Company data);

    /**
     * 统计公司总数
     *
     * @return 数量
     */
    @Select("select id from tb_company order by id desc limit 1")
    int countCompany();

    /**
     * 按id查询公司
     *
     * @param id 数据id
     * @return Company
     */
    @Select("select * from tb_company where id=#{id}")
    Company selectById(int id);

    /**
     * 按id查询公司
     *
     * @param id 数据id
     * @return Company
     */
    @Select("select id,name,contact,email from tb_company where id=#{id}")
    Company selectBriefById(int id);

    /**
     * 按id查询公司
     *
     * @param id 数据id
     * @return Company
     */
    @Select("select id,name,nature,industry from tb_company where id=#{id}")
    Company selectAnalyticById(int id);

    /**
     * 更新公司logo
     *
     * @param logo logo url
     * @param id   公司id
     * @return 0/1
     */
    @Update("update tb_company set logo=#{logo} where id=#{id}")
    int updateLogo(String logo, int id);

    /**
     * 列出所有公司
     *
     * @return List<Company>
     */
    @Select("select id, name, logo, description from tb_company")
    List<Company> listAll();
}
