package cn.globalyouth.cosineapi.dao.ad;

import cn.globalyouth.cosineapi.model.bean.ad.Banner;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

/**
 * @author liuyufeng
 * tb_banner 广告图dao
 */
@Mapper
public interface BannerDao {
  /**
   * 列出页面中的banner
   *
   * @param position1 页面号
   * @param position2 页面号
   * @return List<Banner>
   */
  @Select(
    "select * from tb_banner where position between #{position1} and #{position2} order by position"
  )
  @Results(
    id = "bannerMapper",
    value = { @Result(column = "add_time", property = "addTime") }
  )
  List<Banner> listBanners(
      @Param("position1") int position1, 
    @Param("position2") int position2);
}
