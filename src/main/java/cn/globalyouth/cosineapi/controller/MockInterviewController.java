package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.MockInterviewDto;
import cn.globalyouth.cosineapi.service.IMockInterviewService;
import cn.globalyouth.cosineapi.model.vo.req.JoinMockReqVo;
import cn.globalyouth.cosineapi.model.vo.req.ListableReqVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 模拟面试控制器
 */
@RestController
@RequestMapping("/cosine/mock")
public class MockInterviewController {

    @Resource
    private IMockInterviewService service;

    @PostMapping("/list")
    public ApiResponse<List<MockInterviewDto>> listMockInterviews(@RequestBody ListableReqVo reqVo) {
        return service.listMockInterviews(reqVo.getIndustryId(), reqVo.getTime());
    }

    @PostMapping("/join")
    public ApiResponse<Object> addJoinMock(@RequestBody JoinMockReqVo data) {
        return service.addJoinMock(data);
    }

    @PostMapping("/exit")
    public ApiResponse<Object> delJoinMock(@RequestBody JoinMockReqVo data) {
        return service.delJoinMock(data);
    }
}
