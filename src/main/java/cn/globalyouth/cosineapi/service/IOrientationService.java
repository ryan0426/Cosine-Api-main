package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.enterprise.Orientation;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;

import java.util.List;

/**
 * 宣讲会
 */
public interface IOrientationService {
    /**
    * 添加宣讲会
    *
    * @param orientation 宣讲会信息
    * @return ApiResponse<Object>
    */
    ApiResponse<Object> addOrientation(Orientation orientation);

    /**
    * 删除宣讲会
    *
    * @param id 宣讲会id
    * @return ApiResponse<Object>
    */
    ApiResponse<Object> deleteOrientation(int id);

    /**
    * 获取宣讲会列表
    *
    * @return ApiResponse<List<Orientation>>
    */
    ApiResponse<List<Orientation>> listOrientation();

    /**
    * 搜索宣讲会
    *
    * @param name 模糊查询匹配值
    * @return ApiResponse<List<Orientation>>
    */
    ApiResponse<List<Orientation>> searchOrientation(String name);

}