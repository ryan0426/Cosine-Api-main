package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.user.UserIntention;
//import cn.globalyouth.cosineapi.pojo.bean.send.Job;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.user.UserDto;
import cn.globalyouth.cosineapi.model.dto.user.UserIntentionDto;
import cn.globalyouth.cosineapi.model.dto.user.UserInterestDto;
import cn.globalyouth.cosineapi.model.vo.req.PhoneLoginReqVo;
import cn.globalyouth.cosineapi.model.vo.req.UserInfoReqVo;
import cn.globalyouth.cosineapi.model.vo.req.WechatLoginReqVo;
import org.springframework.web.multipart.MultipartFile;
import cn.globalyouth.cosineapi.model.dto.user.SendStateDto;
import cn.globalyouth.cosineapi.model.vo.req.SendStateReqVo;

import java.util.List;

/**
 * @author liuyufeng
 * 用户服务
 */
public interface IUserService {
  /**
   * 微信登录
   *
   * @param reqVo 登录请求对象
   * @return ApiResponse<UserDto>
   */
  ApiResponse<UserDto> loginByWechat(WechatLoginReqVo reqVo);

  /**
   * 微信手机号登录
   *
   * @param reqVo 登录请求对象
   * @return ApiResponse<UserDto>
   */
  ApiResponse<UserDto> loginByPhone(PhoneLoginReqVo reqVo);

  /**
   * 修改个人信息
   *
   * @param reqVo 请求对象
   * @return ApiResponse<UserDto>
   */
  ApiResponse<Object> updateInfo(UserInfoReqVo reqVo);

  /**
   * 上传头像
   *
   * @param profile 头像文件
   * @return ApiResponse<String>
   */
  ApiResponse<String> uploadProfile(MultipartFile profile);

  /**
   * 添加求职意向
   *
   * @param data 数据
   * @return ApiResponse<Object>
   */
  ApiResponse<Object> addIntention(UserIntention data);

  /**
   * 修改求职意向
   *
   * @param data 数据
   * @return ApiResponse<Object>
   */
  ApiResponse<Object> updateIntention(UserIntention data);

  /**
   * 查询求职意向
   *
   * @param userId 用户id
   * @return ApiResponse<UserIntentionDto>
   */
  ApiResponse<UserIntentionDto> selectIntention(int userId);

  /**
   * 添加岗位到关注列表
   *
   * @param userId 用户id
   * @param id 职位id
   * @param addTime 添加关注时间戳
   * @return ApiResponse<object>
   */
  ApiResponse<Object> addInterest(int userId, int id, long addTime);

  /**
   * 从关注列表取消岗位
   *
   * @param userId 用户id
   * @param id 职位id
   * @return ApiResponse<object>
   */
  ApiResponse<Object> deleteInterest(int userId, int jobId);

  /**
   * 查询关注列表
   *
   * @param userId 用户id
   * @param sortType 排序类别
   * @return ApiResponse<List<Job>>
   */
  ApiResponse<List<UserInterestDto>> displayInterest(int userId, int sortType);

  /**
   * 查询关注
   *
   * @param userId 用户id
   * @param id 职位id
   * @return ApiResponse<Boolean>
   */
  ApiResponse<Boolean> checkInterest(int userId, int id);

  /**
   * 更新投递进度
   *
   * @param reqVo
   * @return ApiResponse<Object>
   */
  ApiResponse<Object> updateSend(SendStateReqVo reqVo);

  /**
   * 获取投递进度列表
   *
   * @param userId 用户id
   * @param sortType 排序
   * @return ApiResponse<List<SendStateDto>>
   */
  ApiResponse<List<SendStateDto>> listSend(int userId, int sortType);

  /**
   * 查询投递进度列表
   *
   * @param userId 用户id
   * @param sortType 排序
   * @param name 公司岗位模糊查询
   * @return ApiResponse<List<SendStateDto>>
   */
  ApiResponse<List<SendStateDto>> searchSend(int userId, int sortType, String name);
}
