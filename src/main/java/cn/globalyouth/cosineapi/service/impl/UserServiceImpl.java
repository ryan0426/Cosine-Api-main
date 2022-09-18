package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.model.bean.user.UserIntention;
import cn.globalyouth.cosineapi.model.bean.user.UserInterest;
import cn.globalyouth.cosineapi.model.bean.send.Send;
import cn.globalyouth.cosineapi.dao.user.UserDao;
import cn.globalyouth.cosineapi.dao.user.UserIntentionDao;
import cn.globalyouth.cosineapi.dao.user.UserInterestDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.user.UserDto;
import cn.globalyouth.cosineapi.model.dto.user.UserIntentionDto;
import cn.globalyouth.cosineapi.model.dto.user.UserInterestDto;
import cn.globalyouth.cosineapi.service.IUserService;
import cn.globalyouth.cosineapi.model.vo.req.PhoneLoginReqVo;
import cn.globalyouth.cosineapi.model.vo.req.UserInfoReqVo;
import cn.globalyouth.cosineapi.model.vo.req.WechatLoginReqVo;
import cn.globalyouth.cosineapi.model.dto.user.SendStateDto;
import cn.globalyouth.cosineapi.model.vo.req.SendStateReqVo;
import cn.globalyouth.cosineapi.dao.send.SendDao;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 *
 *
 * @author liuyufeng 用户服务
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

  @Resource
  private UserDao userDao;

  @Resource
  private UserIntentionDao intentionDao;

  @Resource
  private UserInterestDao interestDao;

  @Resource
  private SendDao sendDao;

  @Value("${baseurl}")
  private String baseUrl;

  @Value("${profileFilePath}")
  private String filePath;

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM");

  @Override
  public ApiResponse<UserDto> loginByWechat(WechatLoginReqVo reqVo) {
    try {
      User user = userDao.selectByOpenid(reqVo.getOpenid());
      if (null != user) {
        User u = new User();
        u.setId(user.getId());
        u.setLoginTime(new Timestamp(System.currentTimeMillis()));
        userDao.update(u);
        return ApiResponse.success(handleUser(user));
      }
      if (1 == userDao.addByWechat(handleUser(reqVo))) {
        user = userDao.selectByOpenid(reqVo.getOpenid());
        if (null != user) {
          return ApiResponse.success(handleUser(user));
        }
        return ApiResponse.error();
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<UserDto> loginByPhone(PhoneLoginReqVo reqVo) {
    try {
      User user = userDao.selectByOpenid(reqVo.getOpenid());
      if (null != user) {
        User u = new User();
        u.setId(user.getId());
        u.setLoginTime(new Timestamp(System.currentTimeMillis()));
        userDao.update(u);
        return ApiResponse.success(handleUser(user));
      }
      if (1 == userDao.addByPhone(handleUser(reqVo))) {
        user = userDao.selectByOpenid(reqVo.getOpenid());
        if (null != user) {
          return ApiResponse.success(handleUser(user));
        }
        return ApiResponse.error();
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> updateInfo(UserInfoReqVo reqVo) {
    try {
      if (1 == userDao.update(handleUser(reqVo))) {
        return ApiResponse.success(null);
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<String> uploadProfile(MultipartFile profile) {
    try {
      String oriName = profile.getOriginalFilename();
      if (null == oriName) {
        return ApiResponse.error(403, "empty file");
      }
      String prefix = oriName.substring(oriName.lastIndexOf("."));
      // 文件存储名称 100000000.jpg
      String fileName = System.currentTimeMillis() + prefix;
      // 文件存储完整路径
      String filePath = this.filePath + File.separator + fileName;
      File file = new File(filePath);
      profile.transferTo(file);
      return ApiResponse.success(baseUrl + "/" + fileName);
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> addIntention(UserIntention data) {
    try {
      if (1 == intentionDao.addIntention(data)) {
        return ApiResponse.success(null);
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> updateIntention(UserIntention data) {
    try {
      if (1 == intentionDao.updateIntention(data)) {
        return ApiResponse.success(null);
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();

    }
  }

  @Override
  public ApiResponse<UserIntentionDto> selectIntention(int userId) {
    try {
      return ApiResponse.success(handleIntention(intentionDao.selectByUser(userId)));
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> addInterest(int userId, int id, long addTime) {
    try {
      Timestamp addTimestamp = new Timestamp(addTime);
      if (1 == interestDao.addInterest(userId, id, addTimestamp)) {
        return ApiResponse.success(null);
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> deleteInterest(int userId, int jobId) {
    try {
      if (1 == interestDao.deleteInterest(userId, jobId)) {
        return ApiResponse.success(null);
      }
      return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<List<UserInterestDto>> displayInterest(int userId, int sortType) {
    try {
      List<UserInterestDto> interestList = handleInterest(interestDao.displayInterest(userId, sortType));
      return ApiResponse.success(interestList);
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Boolean> checkInterest(int userId, int id) {
    try {
      if (0 == interestDao.checkInterest(userId,id)) {
        return ApiResponse.success(false);
        } 
        return ApiResponse.success(true);
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<Object> updateSend(SendStateReqVo reqVo) {    
    try {
      if (1 == sendDao.updateSend(reqVo)) {
        return ApiResponse.success(null);
        } 
        return ApiResponse.error();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<List<SendStateDto>> listSend(int userId, int sortType) {
    try {
      return ApiResponse.success(handleSend(sendDao.listSend(userId,sortType)));
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  @Override
  public ApiResponse<List<SendStateDto>> searchSend(int userId, int sortType, String name) {
    try {
      return ApiResponse.success(handleSend(sendDao.searchSend(userId,sortType,name)));
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      log.error(sw.toString() + "\r\n\r\n");
      return ApiResponse.error();
    }
  }

  private UserDto handleUser(User user) {
    UserDto res = new UserDto();
    res.setId(user.getId());
    res.setName(user.getName());
    res.setGender(user.getGender());
    res.setProfile(user.getProfile());
    res.setOpenid(user.getOpenid());
    res.setPhone(user.getPhone());
    res.setPassword(user.getPassword());
    res.setSchool(user.getSchool());
    res.setMajor(user.getMajor());
    res.setGrade(user.getGrade());
    res.setSignature(user.getSignature());
    res.setIntroduction(user.getIntroduction());
    res.setVip(user.getVip());
    return res;
  }

  private User handleUser(WechatLoginReqVo reqVo) {
    User res = new User();
    res.setOpenid(reqVo.getOpenid());
    res.setName(reqVo.getName());
    res.setProfile(reqVo.getProfile());
    res.setGender(reqVo.getGender());
    return res;
  }

  private User handleUser(PhoneLoginReqVo reqVo) {
    User res = new User();
    res.setOpenid(reqVo.getOpenid());
    res.setPhone(reqVo.getPhone());
    return res;
  }

  private User handleUser(UserInfoReqVo reqVo) {
    User res = new User();
    res.setId(reqVo.getId());
    res.setProfile(reqVo.getProfile());
    res.setName(reqVo.getName());
    res.setGender(reqVo.getGender());
    res.setSchool(reqVo.getSchool());
    res.setMajor(reqVo.getMajor());
    res.setGrade(reqVo.getGrade());
    res.setSignature(reqVo.getSignature());
    if (null != reqVo.getLoginTime()) {
      res.setLoginTime(new Timestamp(reqVo.getLoginTime()));
    }
    return res;
  }

  private UserIntentionDto handleIntention(UserIntention intention) {
    if (null == intention) {
      return null;
    }
    UserIntentionDto res = new UserIntentionDto();
    res.setId(intention.getId());
    res.setPositions(Arrays.asList(intention.getPositions().split(",")));
    res.setIndustries(Arrays.asList(intention.getIndustries().split(",")));
    res.setCities(Arrays.asList(intention.getCities().split(",")));
    res.setIntentionCharacter(Arrays.asList(intention.getIntentionCharacter().split(",")));
    res.setInternLength(intention.getInternLength());
    res.setArrivalTime(FORMAT.format(intention.getArrivalTime()));
    return res;
  }


    private List<UserInterestDto> handleInterest(List<UserInterest> interest_list) {
    if (null == interest_list || interest_list.size() == 0) {
      return null;
    }
    List<UserInterestDto> res_list = new ArrayList<UserInterestDto>();

    for (UserInterest interest : interest_list){
      UserInterestDto res = new UserInterestDto();

      res.setId(interest.getJob().getId());
      res.setTitle(interest.getJob().getTitle());
      res.setMinSalary(interest.getJob().getMinSalary());
      res.setMaxSalary(interest.getJob().getMaxSalary());
      res.setSalary(interest.getJob().getSalary());
      res.setLocation(interest.getJob().getLocation());
      res.setEducation(interest.getJob().getEducation());
      res.setIsEager(interest.getJob().getIsEager());
      res.setCanOnline(interest.getJob().getCanOnline());
      res.setDeadline(interest.getJob().getDeadline());
      res.setAddTime(interest.getJob().getAddTime());
      res.setDescription(interest.getJob().getDescription());
      res.setDemand(interest.getJob().getDemand());
      res.setAddress(interest.getJob().getAddress());
      res.setShow(interest.getJob().getShow());
      res.setCompany(interest.getJob().getCompany());
      res.setViewCnt(interest.getJob().getViewCnt());
      res.setSendCnt(interest.getJob().getSendCnt());
      res.setSubscribeTime(interest.getSubscribe_time());

      res_list.add(res);
    }
    return res_list;
  }

  private List<SendStateDto> handleSend(List<Send> send_list) {
    if (null == send_list || send_list.size() == 0) {
      return null;
    }
    List<SendStateDto> res_list = new ArrayList<SendStateDto>();
    for (Send send : send_list) {
      SendStateDto res = new SendStateDto();
      res.setId(send.getId());
      res.setJobId(send.getJob().getId());
      res.setState(send.getState());
      res.setTitle(send.getJob().getTitle());
      res.setComName(send.getCompany().getName());
      res.setComLogo(send.getCompany().getLogo());
      res.setAddTime(send.getAddTime());
      res.setCompNum(send.getJob().getSendCnt());
      res.setDate1(send.getDate1());
      res.setDate2(send.getDate2());
      res.setDate3(send.getDate3());
      res.setDate4(send.getDate4());
      res_list.add(res);
    }
    return res_list;
  }
}
