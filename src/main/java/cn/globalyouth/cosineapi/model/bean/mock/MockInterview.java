package cn.globalyouth.cosineapi.model.bean.mock;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liuyufeng, Rujun Yan
 * tb_mock_interview 模拟面试
 */
@Data
public class MockInterview {

    private Integer id;

    private String title;

    private Timestamp time;

    private Integer industry;

    private Integer contain;

    private String link;

    private String content;

    private Integer initiator;

    private Integer view;

    private Timestamp addTime;

}
