package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.model.bean.send.Company;
import cn.globalyouth.cosineapi.model.bean.send.Job;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.vo.req.JobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SimpleListReqVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author liuyufeng, Rujun Yan
 * 职位服务
 */
public interface IJobService {

    /**
     * 添加公司
     *
     * @param company 公司对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addCompany(Company company);

    /**
     * 添加职位
     *
     * @param job 职位对象
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> addJob(Job job);

    /**
     * 按id查询公司
     *
     * @param id 数据id
     * @return ApiResponse<Company>
     */
    ApiResponse<Company> selectCompany(int id);

    /**
     * 按id查询职位
     *
     * @param id     数据id
     * @param userId 用户id
     * @return ApiResponse<Job>
     */
    ApiResponse<Job> selectJob(int id, int userId);

    /**
     * 按id查询职位
     *
     * @param ids 数据id
     * @return ApiResponse<List < Job>>
     */
    ApiResponse<List<Job>> listJobs(SimpleListReqVo ids);

    /**
     * 列出推荐职位
     *
     * @param userId    用户id
     * @param changeCnt 点击换一换的次数
     * @return ApiResponse<List < Job>>
     */
    ApiResponse<List<Job>> listRecommendJobs(int userId, int changeCnt);

    /**
     * 按名称列出职位
     *
     * @param name 名称模糊匹配
     * @param page 第几页
     * @return ApiResponse<List < Job>>
     */
    ApiResponse<List<Job>> listByName(String name, int page);

    /**
     * 按条件筛选职位
     *
     * @param reqVo 请求对象
     * @return ApiResponse<List < Job>>
     */
    ApiResponse<List<Job>> listByCondition(JobReqVo reqVo);

    /**
     * 按公司查询
     *
     * @param companyId 公司id
     * @param name      职位名称
     * @param page      页数
     * @return ApiResponse<List < Job>>
     */
    ApiResponse<List<Job>> listByCompany(int companyId, String name, int page);

    /**
     * 浏览量+1
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> incViewCnt(int id);

    /**
     * 投递量+1
     *
     * @param id 数据id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> incSendCnt(int id);

    /**
     * 批量导入职位
     *
     * @param filePath xlsx存储地址
     * @return ApiResponse<Map < String, Object>>
     */
    ApiResponse<Map<String, Object>> batchImportJobs(String filePath);

    /**
     * 批量导入职位
     *
     * @param file xlsx文件
     * @return ApiResponse<Map < String, Object>>
     */
    ApiResponse<Map<String, Object>> batchImportJobs(MultipartFile file);

    /**
     * 上传公司logo
     *
     * @param file 文件对象
     * @param id   公司id
     * @return ApiResponse<Object>
     */
    ApiResponse<Object> uploadCompanyLogo(MultipartFile file, int id);

    /**
     * 编辑职位为官网投递
     *
     * @param fromId  职位第一个id
     * @param toId    职位最后一个id
     * @param website 官网地址
     * @return ApiResponse<Object>
     */
    ApiResponse<Integer> updateWebsiteSendJob(int fromId, int toId, String website);

    /**
     * 列出所有公司
     *
     * @return ApiResponse<List < Company>>
     */
    ApiResponse<List<Company>> listAllCompany();
}
