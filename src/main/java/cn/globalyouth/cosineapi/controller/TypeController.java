package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.model.bean.type.Position;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.calendar.CustomTypeDto;
import cn.globalyouth.cosineapi.model.dto.type.IndustryDto;
import cn.globalyouth.cosineapi.model.dto.type.ZoneDto;
import cn.globalyouth.cosineapi.service.IPositionService;
import cn.globalyouth.cosineapi.service.ISchoolService;
import cn.globalyouth.cosineapi.service.ITypeService;
import cn.globalyouth.cosineapi.model.vo.req.CustomTypeReqVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng
 * 分类类目控制器
 */
@RestController
@RequestMapping("/cosine/type")
public class TypeController {

    @Resource
    private ITypeService typeService;
    @Resource
    private ISchoolService schoolService;
    @Resource
    private IPositionService positionService;

    @GetMapping("/industry/mock")
    public ApiResponse<List<IndustryDto>> listMockIndustries() {
        return typeService.listMockIndustries();
    }

    @GetMapping("/industry/college")
    public ApiResponse<List<IndustryDto>> listCollegeIndustries() {
        return typeService.listCollegeIndustries();
    }

    @GetMapping("/industry/all")
    public ApiResponse<List<IndustryDto>> listAllIndustries() {
        return typeService.listAllIndustries();
    }

    @GetMapping("/zone")
    public ApiResponse<List<ZoneDto>> listAllZones() {
        return typeService.listAllZones();
    }

    @GetMapping("/style")
    public ApiResponse<List<CustomTypeDto>> listAllStyles(int userId) {
        return typeService.listAllStyles(userId);
    }

    @PostMapping("/style")
    public ApiResponse<Object> updateStyle(@RequestBody CustomTypeReqVo reqVo) {
        return typeService.updateStyle(reqVo);
    }

    @PostMapping("/school")
    public ApiResponse<Integer> batchAddSchool(String filePath) {
        return schoolService.batchAddSchool(filePath);
    }

    @GetMapping("/school")
    public ApiResponse<List<String>> listSchool(String name) {
        return schoolService.listSchool(name);
    }

    @PostMapping("/major")
    public ApiResponse<Integer> batchAddMajor() {
        return schoolService.batchAddMajor();
    }

    @GetMapping("/major")
    public ApiResponse<List<String>> listMajor(String name) {
        return schoolService.listMajor(name);
    }

    @PostMapping("/position")
    public ApiResponse<Integer> batchAddPosition() {
        return positionService.batchAddPosition();
    }

    @GetMapping("/position/type")
    public ApiResponse<List<Position>> listPositionTypes() {
        return positionService.listPositionTypes();
    }

    @GetMapping("/position")
    public ApiResponse<List<Position>> listByType(String typeCode) {
        return positionService.listByType(typeCode);
    }

    @GetMapping("/position/name")
    public ApiResponse<String> selectById(String id) {
        return positionService.selectById(Integer.parseInt(id));
    }

}
