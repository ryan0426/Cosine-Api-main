package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

/**
 * @author liuyufeng
 * 加入模面的请求对象
 */
@Data
public class JoinMockReqVo {

    private Integer user;

    private Integer mock;

    private Long addTime;
}
