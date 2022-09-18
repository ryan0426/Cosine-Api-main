package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.model.bean.user.UserIntention;
//import cn.globalyouth.cosineapi.pojo.bean.send.Job;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.user.UserDto;
import cn.globalyouth.cosineapi.model.dto.user.UserIntentionDto;
import cn.globalyouth.cosineapi.model.dto.user.UserInterestDto;
import cn.globalyouth.cosineapi.model.dto.user.SendStateDto;
import cn.globalyouth.cosineapi.model.vo.req.SendStateReqVo;
import cn.globalyouth.cosineapi.service.IUserService;
import cn.globalyouth.cosineapi.model.vo.req.PhoneLoginReqVo;
import cn.globalyouth.cosineapi.model.vo.req.UserInfoReqVo;
import cn.globalyouth.cosineapi.model.vo.req.WechatLoginReqVo;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 用户控制器
 */
@RestController
@RequestMapping("/cosine/user")
public class UserController {

  @Resource
  private IUserService service;

  @PostMapping("/login/wechat")
  public ApiResponse<UserDto> loginByWechat(
    @RequestBody WechatLoginReqVo reqVo
  ) {
    return service.loginByWechat(reqVo);
  }

  @PostMapping("/login/wechatphone")
  public ApiResponse<UserDto> loginByPhone(@RequestBody PhoneLoginReqVo reqVo) {
    return service.loginByPhone(reqVo);
  }

  @PostMapping("/update/info")
  public ApiResponse<Object> updateInfo(@RequestBody UserInfoReqVo reqVo) {
    return service.updateInfo(reqVo);
  }

  @PostMapping("/upload/profile")
  public ApiResponse<String> uploadProfile(MultipartFile profile) {
    return service.uploadProfile(profile);
  }

  @PostMapping("/intention/add")
  public ApiResponse<Object> addIntention(@RequestBody UserIntention data) {
    return service.addIntention(data);
  }

  @PostMapping("/intention/update")
  public ApiResponse<Object> updateIntention(@RequestBody UserIntention data) {
    return service.updateIntention(data);
  }

  @GetMapping("/intention")
  public ApiResponse<UserIntentionDto> selectIntention(String userId) {
    return service.selectIntention(Integer.parseInt(userId));
  }

  @PostMapping("/interest/add")
  public ApiResponse<Object> addInterest(
    int userId,
    int jobId,
    long subscribeTime
  ) {
    return service.addInterest(userId, jobId, subscribeTime);
  }

  @PostMapping("/interest/delete")
  public ApiResponse<Object> deleteInterest(int userId, int jobId) {
    return service.deleteInterest(userId, jobId);
  }

  @PostMapping("/interest/list")
  public ApiResponse<List<UserInterestDto>> displayInterest(int userId,int sortType) {
    return service.displayInterest(userId,sortType);
  }

  @PostMapping("/interest/check")
  public ApiResponse<Boolean> checkInterest(int userId, int jobId) {
    return service.checkInterest(userId,jobId);
  }

  @PostMapping("/send/update")
  public ApiResponse<Object> updateSend(@RequestBody SendStateReqVo reqVo) {
    return service.updateSend(reqVo);
  }

  @PostMapping("/send/list")
  public ApiResponse<List<SendStateDto>> listSend(int userId, int sortType) {
    return service.listSend(userId,sortType);
  }

  @PostMapping("/send/search")
  public ApiResponse<List<SendStateDto>> searchSend(int userId, int sortType, String name) {
    return service.searchSend(userId,sortType,name);
  }

}
