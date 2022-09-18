package cn.globalyouth.cosineapi.dao.mock;

import cn.globalyouth.cosineapi.model.bean.mock.JoinMock;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyufeng
 * tb_join_mock 参加模面dao
 */
@Mapper
public interface JoinMockDao {

    /**
     * 查询参加模面的用户
     *
     * @param mockId 模面id
     * @return List<JoinMock>
     */
    @Select("select * from tb_join_mock where mock=#{mockId} order by add_time desc")
    List<JoinMock> listMockUsers(int mockId);

    /**
     * 统计用户是否已加入
     *
     * @param mockId 模面id
     * @param userId 用户id
     * @return 0/1
     */
    @Select("select count(*) from tb_join_mock where mock=#{mockId} and user=#{userId}")
    int countMockUser(int mockId, int userId);

    /**
     * 添加参与模面用户
     *
     * @param data 添加的对象
     * @return 0/1
     */
    @Insert("insert into tb_join_mock(user, mock, add_time) values (#{user}, #{mock}, #{addTime})")
    int addMockUser(JoinMock data);

    /**
     * 删除参与模面用户
     *
     * @param data 删除的对象
     * @return 0/1
     */
    @Delete("delete from tb_join_mock where user=#{user} and mock=#{mock}")
    int delMockUser(JoinMock data);
}
