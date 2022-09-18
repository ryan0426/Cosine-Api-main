package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.calendar.CustomTypeDto;
import cn.globalyouth.cosineapi.model.dto.type.IndustryDto;
import cn.globalyouth.cosineapi.model.dto.type.ZoneDto;
import cn.globalyouth.cosineapi.model.vo.req.CustomTypeReqVo;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 分类类目服务
 */
public interface ITypeService {

    /**
     * 查询mock行业
     *
     * @return ApiResponse<List < IndustryDto>>
     */
    ApiResponse<List<IndustryDto>> listMockIndustries();

    /**
     * 查询学院行业
     *
     * @return ApiResponse<List < IndustryDto>>
     */
    ApiResponse<List<IndustryDto>> listCollegeIndustries();

    /**
     * 查询所有行业
     *
     * @return ApiResponse<List < IndustryDto>>
     */
    ApiResponse<List<IndustryDto>> listAllIndustries();

    /**
     * 查询全部时区
     *
     * @return ApiResponse<List < ZoneDto>>
     */
    ApiResponse<List<ZoneDto>> listAllZones();

    /**
     * 查询全部用户自定义事件
     *
     * @param userId 用户id
     * @return ApiResponse<List < CustomStyleDto>>
     */
    ApiResponse<List<CustomTypeDto>> listAllStyles(int userId);

    /**
     * 更新自定义事件类型
     *
     * @param reqVo 请求数据
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateStyle(CustomTypeReqVo reqVo);

}
