package cn.globalyouth.cosineapi.controller;

import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.common.utils.OBSUtils;
import cn.globalyouth.cosineapi.common.utils.PdfToImageUtils;
import cn.globalyouth.cosineapi.controller.enterprise.BaseController;
import cn.globalyouth.cosineapi.service.IResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@Api(value = "用户上传简历单独的上传接口", tags = "用户上传简历单独的上传接口")
@RequestMapping("/cosine/resume")
public class NewResumeForUploadController extends BaseController {
    @Autowired
    private IResumeService resumeService;


    @PostMapping("upload")
    @ApiOperation(value = "上传", notes = "上传", httpMethod = "POST")
    public ApiResponse upload(@ApiParam(value = "文件", required = true) MultipartFile file,
                              @RequestParam @ApiParam(value = "用户id", required = true) Integer userId,
                              @RequestParam String title) throws IOException {
        if (file == null || userId == null) {
            return ApiResponse.errorCustom(ResponseEnumState.UPLOAD_FILE_CANT_EMPTY);
        } else {
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isBlank(originalFilename)) {
                return ApiResponse.errorCustom(ResponseEnumState.UPLOAD_FILE_CANT_EMPTY);
            }
            String[] split = originalFilename.split("\\.");
            if (split.length == 0) {
                return ApiResponse.errorCustom(ResponseEnumState.UPLOAD_FILE_CANT_EMPTY);
            }
            //String title = split[0];
            System.out.println(title);
            String suffix = split[split.length - 1];
            String newFileName = UUID.randomUUID().toString()+".";
            if (suffix.equals("pdf")) {
                String pdfUrl = OBSUtils.upload(newFileName + suffix, RESUME_PDF_FOLDER, file.getInputStream());
                byte[] bytes = PdfToImageUtils.pdfToImage2(file.getInputStream());
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                String jpgUrl = OBSUtils.upload(newFileName + PDF_TO_IMAGE_SUFFIX, RESUME_IMAGE_FOLDER, byteArrayInputStream);
                resumeService.createResume(pdfUrl, jpgUrl, userId, title);
            } else {
                return ApiResponse.errorCustom(ResponseEnumState.PLEASE_SELECT_A_PDF_FILE);
            }

        }

        return ApiResponse.ok();
    }
}
