package cn.globalyouth.cosineapi.model.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author liuyufeng
 * 按行业和时间请求Vo
 */
@Data
public class ListableReqVo {

    private Integer industryId;

    private List<String> time;

    private Integer offset;
}
