package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
//import cn.globalyouth.cosineapi.pojo.vo.req.FileReqVo;


/**
 *上传服务
 */
public interface IUploadService {
  ApiResponse<String> upload_oss(String type, String suffix, long length);
  
  ApiResponse<String> query_image_url(String objectKey);
}
