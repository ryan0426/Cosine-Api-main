package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.enums.GenderEnum;
import cn.globalyouth.cosineapi.model.bean.record.*;
import cn.globalyouth.cosineapi.dao.record.*;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.record.*;
import cn.globalyouth.cosineapi.service.IRecordService;
import cn.globalyouth.cosineapi.model.vo.req.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyufeng
 * 履历库服务
 */
@Service
public class RecordServiceImpl implements IRecordService {

    @Resource
    private RecordProfileDao profileDao;
    @Resource
    private RecordBasicDao basicDao;
    @Resource
    private RecordWorkDao workDao;
    @Resource
    private RecordActivityDao activityDao;
    @Resource
    private RecordEducationDao educationDao;
    @Resource
    private RecordAwardDao awardDao;
    @Resource
    private RecordOtherDao otherDao;

    @Value("${baseurl}")
    private String baseUrl;
    @Value("${recordFilePath}")
    private String filePath;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM");

    private static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM");

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
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addProfile(RecordProfileReqVo reqVo) {
        try {
            if (1 == profileDao.addProfile(handleProfile(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addBasic(RecordBasicReqVo reqVo) {
        try {
            if (1 == basicDao.addBasic(handleBasic(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateBasic(RecordBasicReqVo reqVo) {
        try {
            if (1 == basicDao.updateBasic(handleBasic(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordBasicDto> selectBasic(int id) {
        try {
            return ApiResponse.success(handleBasic(basicDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addWork(RecordWorkReqVo reqVo) {
        try {
            if (1 == workDao.addWork(handleWork(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteWork(int id) {
        try {
            if (1 == workDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateWork(RecordWorkReqVo reqVo) {
        try {
            if (1 == workDao.updateWork(handleWork(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordWorkDto> selectWork(int id) {
        try {
            return ApiResponse.success(handleWork(workDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addActivity(RecordActivityReqVo reqVo) {
        try {
            if (1 == activityDao.addActivity(handleActivity(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteActivity(int id) {
        try {
            if (1 == activityDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateActivity(RecordActivityReqVo reqVo) {
        try {
            if (1 == activityDao.updateActivity(handleActivity(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordActivityDto> selectActivity(int id) {
       try {
           return ApiResponse.success(handleActivity(activityDao.selectById(id)));
       } catch (Exception e) {
           e.printStackTrace();
           return ApiResponse.error();
       }
    }

    @Override
    public ApiResponse<Object> addEducation(RecordEducationReqVo reqVo) {
        try {
            if (1 == educationDao.addEducation(handleEducation(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteEducation(int id) {
        try {
            if (1 == educationDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateEducation(RecordEducationReqVo reqVo) {
        try {
            if (1 == educationDao.updateEducation(handleEducation(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordEducationDto> selectEducation(int id) {
        try {
            return ApiResponse.success(handleEducation(educationDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addAward(RecordAwardReqVo reqVo) {
        try {
            if (1 == awardDao.addAward(handleAward(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteAward(int id) {
        try {
            if (1 == awardDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateAward(RecordAwardReqVo reqVo) {
        try {
            if (1 == awardDao.updateAward(handleAward(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordAwardDto> selectAward(int id) {
        try {
            return ApiResponse.success(handleAward(awardDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addOther(RecordOtherReqVo reqVo) {
        try {
            if (1 == otherDao.addOther(handleOther(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteOther(int id) {
        try {
            if (1 == otherDao.deleteById(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateOther(RecordOtherReqVo reqVo) {
        try {
            if (1 == otherDao.updateOther(handleOther(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordOtherDto> selectOther(int id) {
        try {
            return ApiResponse.success(handleOther(otherDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<RecordDto> selectRecord(int userId) {
        try {
            String profile = profileDao.selectLatestProfile(userId);
            RecordBasic basic = basicDao.selectLatestBasic(userId);
            List<RecordWork> workList = workDao.listBriefWork(userId);
            List<RecordActivity> activityList = activityDao.listBriefActivities(userId);
            List<RecordEducation> educationList = educationDao.listBriefEducation(userId);
            List<RecordAward> awardList = awardDao.listBriefAwards(userId);
            List<RecordOther> otherList = otherDao.listBriefOther(userId);
            return ApiResponse.success(handleRecord(profile, basic, workList, activityList, educationList, awardList, otherList));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    private RecordProfile handleProfile(RecordProfileReqVo reqVo) {
        RecordProfile res = new RecordProfile();
        res.setUser(reqVo.getUserId());
        res.setProfile(reqVo.getProfile());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordBasic handleBasic(RecordBasicReqVo reqVo) {
        RecordBasic res = new RecordBasic();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setName(reqVo.getName());
        res.setGender(reqVo.getGender());
        res.setPhone(reqVo.getPhone());
        res.setEmail(reqVo.getEmail());
        res.setLocation(reqVo.getLocation());
        res.setOther(reqVo.getOther());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordBasicDto handleBasic(RecordBasic basic) {
        RecordBasicDto res = new RecordBasicDto();
        res.setId(basic.getId());
        res.setName(basic.getName());
        res.setGender(GenderEnum.getGenderName(basic.getGender()));
        res.setPhone(basic.getPhone());
        res.setEmail(basic.getEmail());
        res.setLocation(basic.getLocation());
        res.setOther(basic.getOther());
        return res;
    }

    private RecordWork handleWork(RecordWorkReqVo reqVo) {
        RecordWork res = new RecordWork();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setCompany(reqVo.getCompany());
        res.setPosition(reqVo.getPosition());
        res.setDepartment(reqVo.getDepartment());
        res.setCity(reqVo.getCity());
        if (null != reqVo.getFromDate()) {
            res.setFromDate(new Date(reqVo.getFromDate().getTime()));
        }
        if (null != reqVo.getToDate()) {
            res.setToDate(new Date(reqVo.getToDate().getTime()));
        }
        res.setDescription(reqVo.getDescription());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordWorkDto handleWork(RecordWork work) {
        RecordWorkDto res = new RecordWorkDto();
        res.setId(work.getId());
        res.setCompany(work.getCompany());
        res.setPosition(work.getPosition());
        res.setDepartment(work.getDepartment());
        res.setCity(work.getCity());
        if (null != work.getFromDate()) {
            res.setFromDate(DATE_FORMAT2.format(work.getFromDate()));
        }
        if (null != work.getToDate()) {
            res.setToDate(DATE_FORMAT2.format(work.getToDate()));
        }
        res.setDescription(work.getDescription());
        return res;
    }

    private RecordActivity handleActivity(RecordActivityReqVo reqVo) {
        RecordActivity res = new RecordActivity();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setActivity(reqVo.getActivity());
        res.setRole(reqVo.getRole());
        res.setDepartment(reqVo.getDepartment());
        res.setCity(reqVo.getCity());
        if (null != reqVo.getFromDate()) {
            res.setFromDate(new Date(reqVo.getFromDate().getTime()));
        }
        if (null != reqVo.getToDate()) {
            res.setToDate(new Date(reqVo.getToDate().getTime()));
        }
        res.setDescription(reqVo.getDescription());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordActivityDto handleActivity(RecordActivity activity) {
        RecordActivityDto res = new RecordActivityDto();
        res.setId(activity.getId());
        res.setActivity(activity.getActivity());
        res.setRole(activity.getRole());
        res.setDepartment(activity.getDepartment());
        res.setCity(activity.getCity());
        if (null != activity.getFromDate()) {
            res.setFromDate(DATE_FORMAT2.format(activity.getFromDate()));
        }
        if (null != activity.getToDate()) {
            res.setToDate(DATE_FORMAT2.format(activity.getToDate()));
        }
        res.setDescription(activity.getDescription());
        return res;
    }

    private RecordAward handleAward(RecordAwardReqVo reqVo) {
        RecordAward res = new RecordAward();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setAward(reqVo.getAward());
        res.setLevel(reqVo.getLevel());
        if (null != reqVo.getDate()) {
            res.setDate(new Date(reqVo.getDate().getTime()));
        }
        res.setDescription(reqVo.getDescription());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordAwardDto handleAward(RecordAward award) {
        RecordAwardDto res = new RecordAwardDto();
        res.setId(award.getId());
        res.setAward(award.getAward());
        res.setLevel(award.getLevel());
        if (null != award.getDate()) {
            res.setDate(DATE_FORMAT2.format(award.getDate()));
        }
        res.setDescription(award.getDescription());
        return res;
    }

    private RecordEducation handleEducation(RecordEducationReqVo reqVo) {
        RecordEducation res = new RecordEducation();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setSchool(reqVo.getSchool());
        res.setEducation(reqVo.getEducation());
        res.setMajor(reqVo.getMajor());
        res.setMinor(reqVo.getMinor());
        res.setCollege(reqVo.getCollege());
        if (null != reqVo.getFromDate()) {
            res.setFromDate(new Date(reqVo.getFromDate().getTime()));
        }
        if (null != reqVo.getToDate()) {
            res.setToDate(new Date(reqVo.getToDate().getTime()));
        }
        res.setDescription(reqVo.getDescription());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordEducationDto handleEducation(RecordEducation education) {
        RecordEducationDto res = new RecordEducationDto();
        res.setId(education.getId());
        res.setSchool(education.getSchool());
        res.setEducation(education.getEducation());
        res.setMajor(education.getMajor());
        res.setMinor(education.getMinor());
        res.setCollege(education.getCollege());
        if (null != education.getFromDate()) {
            res.setFromDate(DATE_FORMAT2.format(education.getFromDate()));
        }
        if (null != education.getToDate()) {
            res.setToDate(DATE_FORMAT2.format(education.getToDate()));
        }
        res.setDescription(education.getDescription());
        return res;
    }

    private RecordOther handleOther(RecordOtherReqVo reqVo) {
        RecordOther res = new RecordOther();
        res.setId(reqVo.getId());
        res.setUser(reqVo.getUserId());
        res.setSkill(reqVo.getSkill());
        res.setLanguage(reqVo.getLanguage());
        res.setCertificate(reqVo.getCertificate());
        res.setHobby(reqVo.getHobby());
        res.setDescription(reqVo.getDescription());
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private RecordOtherDto handleOther(RecordOther other) {
        RecordOtherDto res = new RecordOtherDto();
        res.setId(other.getId());
        res.setSkill(other.getSkill());
        res.setLanguage(other.getLanguage());
        res.setCertificate(other.getCertificate());
        res.setHobby(other.getHobby());
        res.setDescription(other.getDescription());
        return res;
    }

    private RecordDto handleRecord(String profile, RecordBasic basic, List<RecordWork> workList, List<RecordActivity> activityList,
                                   List<RecordEducation> educationList, List<RecordAward> awardList, List<RecordOther> otherList) {
        RecordDto res = new RecordDto();
        res.setProfile(profile);
        if (null != basic) {
            RecordDto.Basic basic1 = new RecordDto.Basic();
            basic1.setId(basic.getId());
            basic1.setName(basic.getName());
            basic1.setGender(GenderEnum.getGenderName(basic.getGender()));
            basic1.setPhone(basic.getPhone());
            basic1.setEmail(basic.getEmail());
            basic1.setLocation(basic.getLocation());
            basic1.setOther(basic.getOther());
            res.setBasic(basic1);
        }
        List<RecordDto.Work> workList1 = new ArrayList<>();
        workList.forEach(recordWork -> {
            RecordDto.Work work = new RecordDto.Work();
            work.setId(recordWork.getId());
            work.setCompany(recordWork.getCompany());
            work.setPosition(recordWork.getPosition());
            work.setDate(DATE_FORMAT.format(recordWork.getFromDate()) + "-" + DATE_FORMAT.format(recordWork.getToDate()));
            workList1.add(work);
        });
        res.setWorkList(workList1);
        List<RecordDto.Activity> activityList1 = new ArrayList<>();
        activityList.forEach(recordActivity -> {
            RecordDto.Activity activity = new RecordDto.Activity();
            activity.setId(recordActivity.getId());
            activity.setActivity(recordActivity.getActivity());
            activity.setRole(recordActivity.getRole());
            activity.setDate(DATE_FORMAT.format(recordActivity.getFromDate()) + "-" + DATE_FORMAT.format(recordActivity.getToDate()));
            activityList1.add(activity);
        });
        res.setActivityList(activityList1);
        List<RecordDto.Education> educationList1 = new ArrayList<>();
        educationList.forEach(recordEducation -> {
            RecordDto.Education education = new RecordDto.Education();
            education.setId(recordEducation.getId());
            education.setSchool(recordEducation.getSchool());
            education.setEducation(recordEducation.getEducation());
            education.setDate(DATE_FORMAT.format(recordEducation.getFromDate()) + "-" + DATE_FORMAT.format(recordEducation.getToDate()));
            educationList1.add(education);
        });
        res.setEducationList(educationList1);
        List<RecordDto.Award> awardList1 = new ArrayList<>();
        awardList.forEach(recordAward -> {
            RecordDto.Award award = new RecordDto.Award();
            award.setId(recordAward.getId());
            award.setAward(recordAward.getAward());
            award.setDate(DATE_FORMAT.format(recordAward.getDate()));
            awardList1.add(award);
        });
        res.setAwardList(awardList1);
        List<RecordDto.Other> otherList1 = new ArrayList<>();
        otherList.forEach(recordOther -> {
            RecordDto.Other other = new RecordDto.Other();
            other.setId(recordOther.getId());
            other.setSkill(recordOther.getSkill());
            other.setLanguage(recordOther.getLanguage());
            other.setCertificate(recordOther.getCertificate());
            other.setHobby(recordOther.getHobby());
            other.setDescription(recordOther.getDescription());
            otherList1.add(other);
        });
        res.setOtherList(otherList1);
        return res;
    }
}
