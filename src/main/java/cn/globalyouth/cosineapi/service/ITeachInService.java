package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.TeachInDto;
import java.util.List;

/**
 * @author liuyufeng
 * 宣讲会服务
 */
public interface ITeachInService {
  /**
   * 查询模拟面试
   *
   * @param name 名称
   * @param vip  是否VIP 0全部 1VIP
   * @return ApiResponse<List < TeachInDto>>
   */
  ApiResponse<List<TeachInDto>> listMockInterviews(String name, int vip);
}
