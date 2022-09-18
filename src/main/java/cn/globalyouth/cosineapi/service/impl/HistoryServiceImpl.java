package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.experience.ExperienceHistory;
import cn.globalyouth.cosineapi.dao.experience.ExperienceHistoryDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.HistoryDto;
import cn.globalyouth.cosineapi.service.IHistoryService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author liuyufeng
 * 搜索历史服务
 */
@Service
public class HistoryServiceImpl implements IHistoryService {

  @Resource
  private ExperienceHistoryDao experienceDao;

  /**
   * 列出模面搜索历史 公司
   *
   * @param userId 用户id
   * @return ApiResponse<List < HistoryDto>>
   */
  @Override
  public ApiResponse<List<HistoryDto>> listExperienceHistoryCompany(
    int userId
  ) {
    try {
      List<HistoryDto> data = new ArrayList<>();
      List<ExperienceHistory> list = experienceDao.listCompanyByUser(userId);
      if (list.size() == 0) {
        list.addAll(experienceDao.listCompanyByHot());
      }
      list.forEach(history -> data.add(handleHistory(history)));
      return ApiResponse.success(data);
    } catch (Exception e) {
      e.printStackTrace();
      return ApiResponse.error();
    }
  }

  /**
   * 列出模面搜索历史 岗位
   *
   * @param userId 用户id
   * @return ApiResponse<List < HistoryDto>>
   */
  @Override
  public ApiResponse<List<HistoryDto>> listExperienceHistoryJob(int userId) {
    try {
      List<HistoryDto> data = new ArrayList<>();
      List<ExperienceHistory> list = experienceDao.listJobByUser(userId);
      if (list.size() == 0) {
        list.addAll(experienceDao.listJobByHot());
      }
      list.forEach(history -> data.add(handleHistory(history)));
      return ApiResponse.success(data);
    } catch (Exception e) {
      e.printStackTrace();
      return ApiResponse.error();
    }
  }

  private HistoryDto handleHistory(ExperienceHistory history) {
    return new HistoryDto(history.getId(), history.getWord());
  }
}
