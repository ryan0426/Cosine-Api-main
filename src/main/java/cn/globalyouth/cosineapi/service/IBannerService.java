package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.BannerDto;

import java.util.List;

/**
 * @author liuyufeng
 * 广告图片服务
 */
public interface IBannerService {

    /**
     * 查询广告图
     * @param position 页面号
     * @return ApiResponse<List<BannerDto>>
     */
    ApiResponse<List<BannerDto>> listBanners(int position);
}
