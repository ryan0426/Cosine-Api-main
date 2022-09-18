package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.model.bean.send.Company;
import cn.globalyouth.cosineapi.model.bean.send.Job;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IJobService;
import cn.globalyouth.cosineapi.model.vo.req.JobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SimpleListReqVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuyufeng
 * 职位控制器
 */
@RestController
@RequestMapping("/cosine")
public class JobController {

    @Resource
    private IJobService service;

    @PostMapping("/company/add")
    public ApiResponse<Object> addCompany(@RequestBody Company company) {
        return service.addCompany(company);
    }

    @GetMapping("/company/id")
    public ApiResponse<Company> selectCompany(String id) {
        return service.selectCompany(Integer.parseInt(id));
    }

    @PostMapping("/job/add")
    public ApiResponse<Object> addJob(@RequestBody Job job) {
        return service.addJob(job);
    }

    @GetMapping("/job/id")
    public ApiResponse<Job> selectJob(String id, String userId) {
        return service.selectJob(Integer.parseInt(id), Integer.parseInt(userId));
    }

    @PostMapping("/job/ids")
    public ApiResponse<List<Job>> listJobs(@RequestBody SimpleListReqVo ids) {
        return service.listJobs(ids);
    }

    @GetMapping("/job/recommend")
    public ApiResponse<List<Job>> listRecommendJobs(String userId, String changeCnt) {
        return service.listRecommendJobs(Integer.parseInt(userId), Integer.parseInt(changeCnt));
    }

    @GetMapping("/job/name")
    public ApiResponse<List<Job>> listByName(String name, String page) {
        return service.listByName(name, Integer.parseInt(page));
    }

    @PostMapping("/job/condition")
    public ApiResponse<List<Job>> listByCondition(@RequestBody JobReqVo reqVo) {
        return service.listByCondition(reqVo);
    }

    @PostMapping("/job/import/path")
    public ApiResponse<Map<String, Object>> batchImportJobs(String filePath) {
        return service.batchImportJobs(filePath);
    }

    @PostMapping("/job/import/file")
    public ApiResponse<Map<String, Object>> batchImportJobs(MultipartFile file) {
        return service.batchImportJobs(file);
    }

    @PostMapping("/job/view")
    public ApiResponse<Object> incViewCnt(String id) {
        return service.incViewCnt(Integer.parseInt(id));
    }

    @PostMapping("/job/send")
    public ApiResponse<Object> incSendCnt(String id) {
        return service.incSendCnt(Integer.parseInt(id));
    }

    @PostMapping("/company/logo")
    public ApiResponse<Object> uploadCompanyLogo(MultipartFile file, String id) {
        return service.uploadCompanyLogo(file, Integer.parseInt(id));
    }

    @PostMapping("/job/website")
    public ApiResponse<Integer> updateWebsiteSendJob(String fromId, String toId, String website) {
        return service.updateWebsiteSendJob(Integer.parseInt(fromId), Integer.parseInt(toId), website);
    }

    @GetMapping("/company/all")
    public ApiResponse<List<Company>> listAllCompany() {
        return service.listAllCompany();
    }

    @GetMapping("/job/company")
    public ApiResponse<List<Job>> listByCompany(String companyId, String name, String page) {
        return service.listByCompany(Integer.parseInt(companyId), name, Integer.parseInt(page));
    }
}
