package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.bean.enterprise.Openday;
import cn.globalyouth.cosineapi.service.IOpendayService;
import org.springframework.stereotype.Service;
import cn.globalyouth.cosineapi.dao.enterprise.OpendayDao;

import java.util.List;
import javax.annotation.Resource;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 开放日服务
 */
@Service 
public class OpendayServiceImpl implements IOpendayService {

    @Resource 
    private OpendayDao opendayDao;

    @Override
    public ApiResponse<Object> addOpenday(Openday openday) {
        try {
            if (1 == opendayDao.addOpenday(openday)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteOpenday(int id) {
        try {
            if (1 == opendayDao.deleteOpenday(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Openday>> listOpenday() {
        try {
            return ApiResponse.success(opendayDao.listOpenday());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Openday>> searchOpenday(String name) {
        try {
            return ApiResponse.success(opendayDao.searchOpenday(name));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Openday>> filterOpenday(String location, String month) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

            if(location.compareTo("不限") == 0) {
                Date date = format.parse(month);
                return ApiResponse.success(opendayDao.filterOpendayMonth(date));
            }
            else if (month.compareTo("不限") == 0) {
                return ApiResponse.success(opendayDao.filterOpendayLoc(location));
            } else {
                Date date = format.parse(month);
                return ApiResponse.success(opendayDao.filterOpenday(location,date));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }
}

