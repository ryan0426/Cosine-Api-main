package cn.globalyouth.cosineapi.model.bean.mock;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng
 * tb_join_mock 参加模面
 */
@Data
public class JoinMock {

    private Integer id;

    private Integer user;

    private Integer mock;

    private Timestamp addTime;

}
