package cn.globalyouth.cosineapi.common.exception;


import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;

public class MyCustomException extends RuntimeException {
    private ResponseEnumState responseEnumState;

    public MyCustomException(ResponseEnumState responseEnumState) {
        super("异常状态码为:"+responseEnumState.getStatus()+" 异常具体信息为:"+responseEnumState.getMsg());
        this.responseEnumState = responseEnumState;
    }

    public ResponseEnumState getResponseEnumState() {
        return responseEnumState;
    }

    public void setResponseEnumState(ResponseEnumState responseEnumState) {
        this.responseEnumState = responseEnumState;
    }
}
