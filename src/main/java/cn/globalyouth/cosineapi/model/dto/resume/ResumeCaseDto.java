package cn.globalyouth.cosineapi.model.dto.resume;

import java.util.List;
import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 简历案例 详细信息
 */
@Data
public class ResumeCaseDto {

  private int id;

  private String title;

  private String introduction;

  private String image;

  private List<String> tags;
}
