package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.vo.req.SendMultiReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SendReqVo;

/**
 * @author liuyufeng, Rujun Yan
 * 投递服务
 */
public interface ISendService {

    /**
     * 投递一份
     *
     * @param reqVo 请求数据
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> sendOne(SendReqVo reqVo);

    /**
     * 投递多份
     *
     * @param reqVo 请求数据
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> sendList(SendMultiReqVo reqVo);

    /**
     * 将某一天的投递导出为xlsx文件
     *
     * @param dateStr 日期字符串
     * @return ApiResponse<String>
     */
    ApiResponse<String> exportSend(String dateStr);

    /**
     * 统计是否已投递
     *
     * @param userId 用户id
     * @param jobId  职位id
     * @return ApiResponse<Boolean>
     */
    ApiResponse<Boolean> isSend(int userId, int jobId);

    /**
     * 官网投递
     *
     * @param userId 用户id
     * @param companyId 公司id
     * @param jobId  职位id
     * @param addTime 当前时间戳
     * @return ApiResponse<Object> 
     */  
    ApiResponse<Object> sendWeb(int userId, int companyId, int jobId, long addTime);
}
