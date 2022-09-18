package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.ad.Banner;
import cn.globalyouth.cosineapi.dao.ad.BannerDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.BannerDto;
import cn.globalyouth.cosineapi.service.IBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;


import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * @author liuyufeng
 * 广告图片服务
 */
@Service
@Slf4j
public class BannerServiceImpl implements IBannerService {

    @Resource
    private BannerDao bannerDao;

    /**
     * 查询广告图
     *
     * @param position 页面号
     * @return ApiResponse<List < BannerDto>>
     */
    @Override
    public ApiResponse<List<BannerDto>> listBanners(int position) {
        try {
            List<BannerDto> data = new ArrayList<>();
            List<Banner> banners = bannerDao.listBanners(position + 1, position + 9);
            banners.forEach(banner -> data.add(new BannerDto(banner.getId(), banner.getImage(), banner.getLink())));
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
e.printStackTrace(pw);
String sStackTrace = sw.toString();
log.error(sStackTrace +"\r\n\r\n");
            return ApiResponse.error();
        }
    }
}
