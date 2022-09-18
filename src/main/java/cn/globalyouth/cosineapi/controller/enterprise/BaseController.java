package cn.globalyouth.cosineapi.controller.enterprise;


import cn.globalyouth.cosineapi.common.utils.JwtTokenUtils;

import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public static final String USER_TOKEN = "token";
    public static final String RESUME_PDF_FOLDER = "resume_pdf";
    public static final String RESUME_IMAGE_FOLDER = "resume_image";
    public static final String PDF_TO_IMAGE_SUFFIX = "jpg";



    public String getHeaderToken(){
        return JwtTokenUtils.getToken();
    }

    public String getUserId(String token){
        return JwtTokenUtils.getUserId(token);
    }


    public String getUserId(){
        return JwtTokenUtils.getUserId(JwtTokenUtils.getToken());
    }


    /**
     * 设置token
     * @param response
     * @param userId
     */
    public void setHeaderToken(HttpServletResponse response, String userId){
        String token = JwtTokenUtils.generateToken(userId,System.currentTimeMillis());
        response.setHeader(USER_TOKEN,token);
    }

}
