package cn.globalyouth.cosineapi.common.utils;

import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import lombok.Data;

/**
 * @author liuyufeng, Rujun Yan
 * api返回结果封装
 */
@Data
public class ApiResponse<T> {

    private Integer code;

    private String msg;

    private T data;


    public ApiResponse() {

    }

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public ApiResponse(T data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }


    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(0);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error() {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(500);
        response.setMsg("service error");
        return response;
    }

    public static <T> ApiResponse<T> error(int code, String msg) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }


    public static <T> ApiResponse<T> build(Integer status, String msg, T data) {
        return new ApiResponse(status, msg, data);
    }

    public static ApiResponse build(Integer status, String msg, Object data, String ok) {
        return new ApiResponse(status, msg, data);
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(data);
    }

    public static ApiResponse ok() {
        return new ApiResponse(null);
    }

    public static ApiResponse errorMsg(String msg) {
        return new ApiResponse(500, msg, null);
    }

    public static ApiResponse errorMap(Object data) {
        return new ApiResponse(500, data.toString(),null);
    }

    public static ApiResponse errorTokenMsg(String msg) {
        return new ApiResponse(502, msg, null);
    }

    public static ApiResponse errorException(String msg) {
        return new ApiResponse(555, msg, null);
    }

    public static ApiResponse errorUserQQ(String msg) {
        return new ApiResponse(556, msg, null);
    }

    public static ApiResponse errorCustom(ResponseEnumState responseEnumState){
        return new ApiResponse(responseEnumState.getStatus(),responseEnumState.getMsg(),null);
    }





    
}