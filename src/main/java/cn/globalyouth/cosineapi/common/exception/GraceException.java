package cn.globalyouth.cosineapi.common.exception;


import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;

public class GraceException {
    public static void display(ResponseEnumState responseEnumState){
        throw new MyCustomException(responseEnumState);
    }
}
