package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.record.*;
import cn.globalyouth.cosineapi.model.vo.req.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuyufeng
 * 履历库服务
 */
public interface IRecordService {

    /**
     * 上传头像
     *
     * @param profile 头像文件
     * @return ApiResponse<String>
     */
    ApiResponse<String> uploadProfile(MultipartFile profile);

    /**
     * 添加履历库头像信息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addProfile(RecordProfileReqVo reqVo);

    /**
     * 添加履历库基本信息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addBasic(RecordBasicReqVo reqVo);

    /**
     * 更新基本信息
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateBasic(RecordBasicReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordBasicDto>
     */
    ApiResponse<RecordBasicDto> selectBasic(int id);

    /**
     * 添加工作经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addWork(RecordWorkReqVo reqVo);


    /**
     * 删除工作经历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteWork(int id);

    /**
     * 更新工作经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateWork(RecordWorkReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordWorkDto>
     */
    ApiResponse<RecordWorkDto> selectWork(int id);

    /**
     * 添加组织活动经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addActivity(RecordActivityReqVo reqVo);

    /**
     * 删除组织活动经历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteActivity(int id);

    /**
     * 更新组织/活动经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateActivity(RecordActivityReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordActivityDto>
     */
    ApiResponse<RecordActivityDto> selectActivity(int id);

    /**
     * 添加教育经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addEducation(RecordEducationReqVo reqVo);

    /**
     * 删除教育经历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteEducation(int id);

    /**
     * 更新教育经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateEducation(RecordEducationReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordEducationDto>
     */
    ApiResponse<RecordEducationDto> selectEducation(int id);

    /**
     * 添加获奖经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addAward(RecordAwardReqVo reqVo);

    /**
     * 删除获奖经历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteAward(int id);

    /**
     * 更新组织/活动经历
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateAward(RecordAwardReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordAwardDto>
     */
    ApiResponse<RecordAwardDto> selectAward(int id);

    /**
     * 添加其他
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addOther(RecordOtherReqVo reqVo);

    /**
     * 删除其他
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteOther(int id);

    /**
     * 更新其他
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateOther(RecordOtherReqVo reqVo);

    /**
     * 按id查询
     *
     * @param id 数据id
     * @return ApiResponse<RecordOtherDto>
     */
    ApiResponse<RecordOtherDto> selectOther(int id);

    /**
     * 列出履历库
     *
     * @param userId 用户id
     * @return ApiResponse<RecordDto>
     */
    ApiResponse<RecordDto> selectRecord(int userId);
}
