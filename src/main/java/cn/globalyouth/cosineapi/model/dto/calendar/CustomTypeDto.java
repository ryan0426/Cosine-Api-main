package cn.globalyouth.cosineapi.model.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng, Rujun Yan
 * 自定义事件类型dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomTypeDto {

  private Integer id;

  private int style;

  private String color;

  private String name;
}
