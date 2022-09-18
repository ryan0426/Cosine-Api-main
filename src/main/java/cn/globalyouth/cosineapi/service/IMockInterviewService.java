package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.MockInterviewDto;
import cn.globalyouth.cosineapi.model.vo.req.JoinMockReqVo;

import java.util.List;

/**
 * @author liuyufeng
 * 模拟面试服务
 */
public interface IMockInterviewService {

    /**
     * 查询模拟面试
     *
     * @param industryId 行业id
     * @param time       时间列表
     * @return ApiResponse<List < MockInterviewDto>>
     */
    ApiResponse<List<MockInterviewDto>> listMockInterviews(Integer industryId, List<String> time);

    /**
     * 添加参与模面用户
     *
     * @param data 添加的对象
     * @return ApiResponse
     */
    ApiResponse<Object> addJoinMock(JoinMockReqVo data);

    /**
     * 删除参与模面用户
     *
     * @param data 删除的对象
     * @return ApiResponse
     */
    ApiResponse<Object> delJoinMock(JoinMockReqVo data);
}
