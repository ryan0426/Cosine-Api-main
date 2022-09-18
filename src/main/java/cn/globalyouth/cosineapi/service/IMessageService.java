package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.vo.req.BindMessageJobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CancelMessageJobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CustomMessageJobReqVo;

/**
 * @author liuyufeng, Rujun Yan
 * 订阅消息服务
 */
public interface IMessageService {

    /**
     * 订阅自定义事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> subscribeCustomMessage(CustomMessageJobReqVo reqVo);

    /**
     * 订阅预置事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> subscribeBindMessage(BindMessageJobReqVo reqVo);

    /**
     * 取消订阅事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> cancelMessage(CancelMessageJobReqVo reqVo);

    /**
     * 更改自定义事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> changeCustomMessage(CustomMessageJobReqVo reqVo);

    /**
     * 更改预置事件消息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> changeBindMessage(BindMessageJobReqVo reqVo);
}
