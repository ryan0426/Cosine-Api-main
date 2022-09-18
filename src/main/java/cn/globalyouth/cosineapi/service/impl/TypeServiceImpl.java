package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.enums.StyleEnum;
import cn.globalyouth.cosineapi.model.bean.calendar.CustomType;
import cn.globalyouth.cosineapi.model.bean.type.Industry;
import cn.globalyouth.cosineapi.model.bean.type.Zone;
import cn.globalyouth.cosineapi.dao.calendar.CustomTypeDao;
import cn.globalyouth.cosineapi.dao.type.IndustryDao;
import cn.globalyouth.cosineapi.dao.type.ZoneDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.calendar.CustomTypeDto;
import cn.globalyouth.cosineapi.model.dto.type.IndustryDto;
import cn.globalyouth.cosineapi.model.dto.type.ZoneDto;
import cn.globalyouth.cosineapi.service.ITypeService;
import cn.globalyouth.cosineapi.model.vo.req.CustomTypeReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 分类类目服务
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Resource
    private IndustryDao industryDao;

    @Resource
    private ZoneDao zoneDao;

    @Resource
    private CustomTypeDao customTypeDao;

    @Override
    public ApiResponse<List<IndustryDto>> listMockIndustries() {
        try {
            List<Industry> list = industryDao.listMock();
            List<IndustryDto> res = new ArrayList<>();
            list.forEach(industry -> res.add(handleIndustry(industry)));
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<IndustryDto>> listCollegeIndustries() {
        try {
            List<Industry> list = industryDao.listCollege();
            List<IndustryDto> res = new ArrayList<>();
            list.forEach(industry -> res.add(handleIndustry(industry)));
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<IndustryDto>> listAllIndustries() {
        try {
            List<Industry> list = industryDao.listAll();
            List<IndustryDto> res = new ArrayList<>();
            list.forEach(industry -> res.add(handleIndustry(industry)));
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 查询全部时区
     *
     * @return ApiResponse<List < ZoneDto>>
     */
    @Override
    public ApiResponse<List<ZoneDto>> listAllZones() {
        try {
            List<Zone> list = zoneDao.listAll();
            List<ZoneDto> res = new ArrayList<>();
            list.forEach(zone -> res.add(handleZone(zone)));
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 查询全部用户自定义事件
     *
     * @param userId 用户id
     * @return ApiResponse<List < CustomStyleDto>>
     */
    @Override
    public ApiResponse<List<CustomTypeDto>> listAllStyles(int userId) {
        try {
            List<CustomTypeDto> data = new ArrayList<>();
            List<CustomType> list = customTypeDao.listAll(userId);
            list.forEach(item -> data.add(handleCustomStyle(item)));
            if (data.stream().noneMatch(e -> e.getStyle() == StyleEnum.CUSTOM_RED.getCode())) {
                data.add(0, new CustomTypeDto(null, StyleEnum.CUSTOM_RED.getCode(), StyleEnum.CUSTOM_RED.getColor(), "自定义事件1"));
            }
            if (data.stream().noneMatch(e -> e.getStyle() == StyleEnum.CUSTOM_YELLOW.getCode())) {
                data.add(1, new CustomTypeDto(null, StyleEnum.CUSTOM_YELLOW.getCode(), StyleEnum.CUSTOM_YELLOW.getColor(), "自定义事件2"));
            }
            if (data.stream().noneMatch(e -> e.getStyle() == StyleEnum.CUSTOM_BLUE.getCode())) {
                data.add(2, new CustomTypeDto(null, StyleEnum.CUSTOM_BLUE.getCode(), StyleEnum.CUSTOM_BLUE.getColor(), "自定义事件3"));
            }
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 更新自定义事件类型
     *
     * @param reqVo 请求数据
     * @return ApiResponse<Object>
     */
    @Override
    public ApiResponse<Object> updateStyle(CustomTypeReqVo reqVo) {
        try {
            Integer dataId = customTypeDao.selectStyle(handleCustomStyle(reqVo));
            if (null == dataId) {
                if (1 == customTypeDao.insertStyle(handleCustomStyle(reqVo))) {
                    return ApiResponse.success(null);
                }
                return ApiResponse.error();
            }
            if (1 == customTypeDao.updateStyle(dataId, reqVo.getName())) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    private IndustryDto handleIndustry(Industry industry) {
        return new IndustryDto(industry.getId(), industry.getName());
    }

    private ZoneDto handleZone(Zone zone) {
        return new ZoneDto(zone.getId(), zone.getName());
    }

    private CustomTypeDto handleCustomStyle(CustomType customType) {
        return new CustomTypeDto(customType.getId(), customType.getStyle(),
                StyleEnum.getColorByCode(customType.getStyle()), customType.getName());
    }

    private CustomType handleCustomStyle(CustomTypeReqVo reqVo) {
        CustomType res = new CustomType();
        res.setUser(reqVo.getUserId());
        res.setStyle(reqVo.getStyle());
        res.setName(reqVo.getName());
        return res;
    }
}
