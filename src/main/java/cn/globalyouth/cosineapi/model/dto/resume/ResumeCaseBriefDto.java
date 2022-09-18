package cn.globalyouth.cosineapi.model.dto.resume;

import java.util.List;
import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * 简历案例 简要信息
 */
@Data
public class ResumeCaseBriefDto {

  private int id;

  private String introduction;

  private List<String> tags;
}
