package cn.globalyouth.cosineapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng, Rujun Yan
 * 广告图dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerDto {

  private int id;

  private String image;

  private String link;
}
