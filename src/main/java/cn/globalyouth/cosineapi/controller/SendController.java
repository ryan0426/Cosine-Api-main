package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.ISendService;
import cn.globalyouth.cosineapi.model.vo.req.SendMultiReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SendReqVo;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuyufeng
 * 投递控制器
 */
@RestController
@RequestMapping("/cosine/send")
public class SendController {

  @Resource
  private ISendService service;

  @PostMapping("/add")
  public ApiResponse<Object> sendOne(@RequestBody SendReqVo reqVo) {
    return service.sendOne(reqVo);
  }

  @PostMapping("/batch")
  public ApiResponse<Integer> sendList(@RequestBody SendMultiReqVo reqVo) {
    return service.sendList(reqVo);
  }

  @PostMapping("/export")
  public ApiResponse<String> exportSend(String dateStr) {
    return service.exportSend(dateStr);
  }

  @GetMapping("/done")
  public ApiResponse<Boolean> isSend(String userId, String jobId) {
    return service.isSend(Integer.parseInt(userId), Integer.parseInt(jobId));
  }

  @PostMapping("/web")
  public ApiResponse<Object> sendWeb(int userId, int companyId, int jobId, long addTime) {
    return service.sendWeb(userId, companyId, jobId, addTime);
  }
}
