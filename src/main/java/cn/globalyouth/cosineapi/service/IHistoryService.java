package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.HistoryDto;

import java.util.List;

/**
 * @author liuyufeng
 * 搜索历史服务
 */
public interface IHistoryService {

    /**
     * 列出模面搜索历史 公司
     *
     * @param userId 用户id
     * @return ApiResponse<List < HistoryDto>>
     */
    ApiResponse<List<HistoryDto>> listExperienceHistoryCompany(int userId);

    /**
     * 列出模面搜索历史 岗位
     *
     * @param userId 用户id
     * @return ApiResponse<List < HistoryDto>>
     */
    ApiResponse<List<HistoryDto>> listExperienceHistoryJob(int userId);
}
