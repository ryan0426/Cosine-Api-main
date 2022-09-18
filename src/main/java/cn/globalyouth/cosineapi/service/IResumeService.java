package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseBriefDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeImportDto;
import cn.globalyouth.cosineapi.model.dto.resume.VideoResumeDto;
import cn.globalyouth.cosineapi.model.pojo.TbResume;
import cn.globalyouth.cosineapi.model.vo.req.ResumeReqVo;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liuyufeng
 * 简历服务
 */
public interface IResumeService {

    /**
     * 根据用户ID查询所有简历
     * @param userId
     * @return
     */
     List<TbResume> queryByUserIdList(Integer userId);

    /**
     * 查询用户第一份简历
     *
     * @param userId 用户id
     * @return ApiResponse<ResumeDto>
     */
     TbResume selectTopResume(int userId);

    /**
     * 按id查询简历
     *
     * @param id 数据id
     * @return ApiResponse<ResumeDto>
     */
    TbResume selectResume(int id);

    /**
     * 统计用户简历
     *
     * @param userId 用户id
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> countResume(int userId);

    /**
     * 设置为默认简历
     *
     * @param userId 用户id
     * @param id     默认简历id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> setDefaultResume(int userId, int id);

    /**
     * 修改名称
     *
     * @param id    数据id
     * @param title 名称
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> updateTitle(int id, String title);

    /**
     * 导入简历，并生成缩略图
     *
     * @param file pdf临时文件
     * @return ApiResponse<ResumeImportDto>
     */
    ApiResponse<ResumeImportDto> importResume(MultipartFile file);

    /**
     * 添加简历数据
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addResume(ResumeReqVo reqVo);

    /**
     * "删除"简历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteResume(int id);

    /**
     * 列出最热门的5份案例
     *
     * @return ApiResponse<List < ResumeCaseDto>>
     */
    ApiResponse<List<ResumeCaseBriefDto>> listHot();

    /**
     * 列出特定行业的案例
     *
     * @param industryId 行业id
     * @return ApiResponse<List < ResumeCaseDto>>
     */
    ApiResponse<List<ResumeCaseBriefDto>> listByIndustry(int industryId);

    /**
     * 展示案例详情
     *
     * @param id 数据id
     * @return ApiResponse<ResumeCaseDto>
     */
    ApiResponse<ResumeCaseDto> selectById(int id);

    /**
     * 添加视频简历数据
     *
     * @param reqVo 请求对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addVideoResume(int userId, String key);

    /**
     * 删除视频简历
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> deleteVideoResume(int id);

    /**
     * 按id查询视频简历
     *
     * @param id 数据id
     * @return ApiResponse<VideoResumeDto>
     */
    ApiResponse<List<VideoResumeDto>> selectVideoResume(int id);


    /**
     * 添加简历到数据库
     * @return
     */
    TbResume createResume(String pdfUrl, String jpgUrl, Integer userId, String title);
}
