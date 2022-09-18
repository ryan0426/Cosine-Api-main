package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.type.Position;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 职位服务
 */
public interface IPositionService {

    /**
     * 通过网页批量添加职位
     *
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> batchAddPosition();

    /**
     * 查询职位类型
     *
     * @return ApiResponse<List < PositionDto>>
     */
    ApiResponse<List<Position>> listPositionTypes();

    /**
     * 按类型查询职位
     *
     * @param typeCode 3位职位类型代码
     * @return ApiResponse<List < PositionDto>>
     */
    ApiResponse<List<Position>> listByType(String typeCode);

    /**
     * 按id查询职位名
     *
     * @param id 职位id
     * @return ApiResponse<String>
     */
    ApiResponse<String> selectById(int id);
}
