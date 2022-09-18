package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IUploadService;
import javax.annotation.Resource;
//import cn.globalyouth.cosineapi.pojo.vo.req.FileReqVo;
import org.springframework.web.bind.annotation.*;

/**
 *上传文件控制器
 */
@RestController
@RequestMapping("/cosine/upload")
public class UploadController {

  @Resource
  private IUploadService service;

  @PostMapping("upload_oss")
  public ApiResponse<String> upload_oss(
    String type,
    String suffix,
    long length
  ) {
    return service.upload_oss(type, suffix, length);
  }

  @PostMapping("query_image_url")
  public ApiResponse<String> query_image_url(String objectKey) {
    return service.query_image_url(objectKey);
  }
}


