package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.enterprise.Openday;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;

import java.util.List;

/**
 * 开放日
 */
public interface IOpendayService {
    /**
    * 添加开放日
    *
    * @param openday 开放日信息
    * @return ApiResponse<Object>
    */
    ApiResponse<Object> addOpenday(Openday openday);

    /**
    * 删除开放日
    *
    * @param id 开放日id
    * @return ApiResponse<Object>
    */
    ApiResponse<Object> deleteOpenday(int id);

    /**
    * 获取开放日列表
    *
    * @return ApiResponse<List<Openday>>
    */
    ApiResponse<List<Openday>> listOpenday();

    /**
    * 搜索开放日
    *
    * @param name 模糊查询匹配值
    * @return ApiResponse<List<Openday>>
    */
    ApiResponse<List<Openday>> searchOpenday(String name);

    /**
    * 筛选开放日
    *
    * @param location 地点
    * @param month 开放日年月
    * @return ApiResponse<List<Openday>>
    */
    ApiResponse<List<Openday>> filterOpenday(String location, String month);
}