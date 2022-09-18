package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IMessageService;
import cn.globalyouth.cosineapi.model.vo.req.BindMessageJobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CancelMessageJobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.CustomMessageJobReqVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyufeng
 * 消息服务控制器
 */
@RestController
@RequestMapping("/cosine/message")
public class MessageController {

    @Resource
    private IMessageService service;

    @PostMapping("/add/custom")
    public ApiResponse<Object> subscribeCustomMessage(@RequestBody CustomMessageJobReqVo reqVo) {
        return service.subscribeCustomMessage(reqVo);
    }

    @PostMapping("/add/bind")
    public ApiResponse<Object> subscribeBindMessage(@RequestBody BindMessageJobReqVo reqVo) {
        return service.subscribeBindMessage(reqVo);
    }

    @PostMapping("/cancel")
    public ApiResponse<Object> cancelMessage(@RequestBody CancelMessageJobReqVo reqVo) {
        return service.cancelMessage(reqVo);
    }

    @PostMapping("/change/custom")
    public ApiResponse<Object> changeCustomMessage(@RequestBody CustomMessageJobReqVo reqVo) {
        return service.changeCustomMessage(reqVo);
    }

    @PostMapping("/change/bind")
    public ApiResponse<Object> changeBindMessage(@RequestBody BindMessageJobReqVo reqVo) {
        return service.changeBindMessage(reqVo);
    }
}
