package cn.globalyouth.cosineapi.common.enums;

import org.springframework.http.HttpStatus;

/**
 * 响应信息的枚举类
 */
public enum ResponseEnumState {
    USERNAME_CANNOT_BE_EMPTY(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名不能为空"),
    THE_USERNAME_OR_EMAIL_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名或邮箱已经存在!"),
    WRONG_USER_NAME_OR_PASSWORD(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名或密码错误!"),
    USER_AUTHENTICATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"身份信息已失效,请重新登录"),
    USER_DOES_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户不存在"),
    THE_PASSWORD_ENTERED_TWICE_IS_INCORRECT(HttpStatus.INTERNAL_SERVER_ERROR.value(),"二次输入的密码不正确!"),
    FAILED_TO_SUBMIT_REGISTRATION_INFORMATION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"提交失败!"),
    ILLEGAL_OPERATION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"非法操作!"),
    INSERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"创建失败!"),
    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"更新失败"),
    PLEASE_SELECT_A_PDF_FILE(HttpStatus.INTERNAL_SERVER_ERROR.value(),"请上传pdf文件!"),
    UPLOAD_FILE_CANT_EMPTY(HttpStatus.INTERNAL_SERVER_ERROR.value(),"未选择文件不能为空"),
    THE_CURRENT_POSITION_IS_CLOSED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"当前职位已处于关闭状态");

    private Integer status;
    private String msg;


    ResponseEnumState(Integer status,String msg){
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
