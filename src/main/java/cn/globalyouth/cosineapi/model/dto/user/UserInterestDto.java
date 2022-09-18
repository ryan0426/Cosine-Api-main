package cn.globalyouth.cosineapi.model.dto.user;

import cn.globalyouth.cosineapi.model.bean.send.Company;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * 关注列表dto
 */
@Data
public class UserInterestDto {

  private Integer id;

  private String title;

  private Integer minSalary;

  private Integer maxSalary;

  private String salary;

  private String location;

  private String education;

  private Integer isEager;

  private String canOnline;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd",
    timezone = "GMT+8"
  )
  private Date deadline;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd HH:mm:ss",
    timezone = "GMT+8"
  )
  private Timestamp addTime;

  private String description;

  private String demand;

  private String address;

  private Integer show;

  private Company company;

  private Integer viewCnt;

  private Integer sendCnt;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd HH:mm:ss",
    timezone = "GMT+8"
  )
  private Timestamp subscribeTime;
}
