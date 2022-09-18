package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.bean.enterprise.Orientation;
import cn.globalyouth.cosineapi.service.IOrientationService;
import org.springframework.stereotype.Service;
import cn.globalyouth.cosineapi.dao.enterprise.OrientationDao;

import java.util.List;
import javax.annotation.Resource;

/**
 * 宣讲会服务
 */
@Service 
public class OrientationServiceImpl implements IOrientationService {

    @Resource 
    private OrientationDao orientationDao;

    @Override
    public ApiResponse<Object> addOrientation(Orientation orientation) {
        try {
            if (1 == orientationDao.addOrientation(orientation)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteOrientation(int id) {
        try {
            if (1 == orientationDao.deleteOrientation(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Orientation>> listOrientation() {
        try {
            return ApiResponse.success(orientationDao.listOrientation());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Orientation>> searchOrientation(String name) {
        try {
            return ApiResponse.success(orientationDao.searchOrientation(name));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

}