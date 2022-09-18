package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IUploadService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * 上传服务
 */
@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {

	private String endPoint = "obs.cn-east-3.myhuaweicloud.com";
	private String ak = "ENZTRZXLWB5HAYASAFYA";
	private String sk = "7MrxwI8gdDuhsxAjARHMVZdsPhPqGTxbhqjLjayM";

	private ObsClient obsClient = new ObsClient(ak, sk, endPoint);



	@Override
	public ApiResponse<String> upload_oss(String type, String suffix, long length) {
		try {

			String uuid = UUID.randomUUID().toString();
			String result = "";

			if (type.equals("resume") && (suffix.equals(".pdf"))) {

				if (suffix.equals(".pdf")){
					String objectKey = "resume/" + uuid + suffix;
					result = JSON.toJSONString(signatureUrl(objectKey, "application/pdf"));
				} else {
					return ApiResponse.error();
				}
			} else if (type.equals("avatar")) {
				if (suffix.equals(".jpg") || suffix.equals(".jpeg")){
					String objectKey = "avatar/" + uuid + suffix;
					result = JSON.toJSONString(signatureUrl(objectKey, "image/jpeg"));
				} else {
					return ApiResponse.error();
				}

			} else if (type.equals("video")) {
				if (suffix.equals(".mp4") || suffix.equals(".mpeg")) {
					String objectKey = "video/" + uuid + suffix;
					result = JSON.toJSONString(signatureUrl(objectKey, "video/mp4"));
				} else {
					return ApiResponse.error();
				}
			} else {
				return ApiResponse.error();
			}

			return ApiResponse.success(result);

		} catch (Exception e) {
			e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
			
			return ApiResponse.error();
		}
	}


	@Override
	public ApiResponse<String> query_image_url(String objectKey) {
		/**
		 *	仅供测试使用
		*/
		try {

      long expireSeconds = 3600L;
			TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
			request.setBucketName("cosine-storage");
			request.setObjectKey(objectKey);

			TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
			String url = response.getSignedUrl();

			//获取支持图片转码的下载链接
			System.out.println("Getting object using temporary signature url:");
			System.out.println("\t" + url);

			return ApiResponse.success(url);

		} catch (Exception e) {
			e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
			return ApiResponse.error();
		}
	}

	private JSONObject signatureUrl(String objectKey, String contentType) {
		long expireSeconds = 3600L;
		Map headers = new HashMap();

		// String contentType = "text/plain";

		headers.put("Content-Type", contentType);

		TemporarySignatureRequest request = new TemporarySignatureRequest(
			HttpMethodEnum.PUT,
			expireSeconds
		);
		request.setBucketName("cosine-storage");
		request.setObjectKey(objectKey);
		request.setHeaders(headers);
		TemporarySignatureResponse response = obsClient.createTemporarySignature(
			request
		);

		Map<String, String> put_headers = response.getActualSignedRequestHeaders();

		for (Map.Entry<String,String> entry : put_headers.entrySet()){
			System.out.println("Key = " + entry.getKey() +
			", Value = " + entry.getValue());
		}

		JSONObject ret = new JSONObject();
		ret.put("url", response.getSignedUrl());
		ret.put("headers", put_headers);
		ret.put("key", objectKey);

		return ret;
	}


}
