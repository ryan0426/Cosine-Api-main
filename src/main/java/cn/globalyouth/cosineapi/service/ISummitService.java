package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.summit.VisaInfo;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;

/**
 * @author liuyufeng, Rujun Yan
 * 峰会接口服务
 */
public interface ISummitService {

    /**
     * 添加信息
     *
     * @param data 数据
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addInfo(VisaInfo data);

    /**
     * 将某一天的预约导出为xlsx文件
     *
     * @param dateStr 日期字符串
     * @return ApiResponse<String>
     */
    ApiResponse<String> exportInfo(String dateStr);
}
