package cn.globalyouth.cosineapi.common.exception;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<String> errors = handlerBindingResult(bindingResult);
        return ApiResponse.errorMap(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException){
            System.out.println(constraintViolationException.getMessage());
        String message = constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ApiResponse.errorMsg(message);
    }

    @ExceptionHandler(MyCustomException.class)
    public ApiResponse myCustomExceptionHandler(MyCustomException myCustomException){
        myCustomException.printStackTrace();
        return ApiResponse.errorCustom(myCustomException.getResponseEnumState());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResponse maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException maxUploadSizeExceededException){
        maxUploadSizeExceededException.printStackTrace();
        return ApiResponse.errorMsg("test");
    }

    private List<String> handlerBindingResult(BindingResult bindingResult) {
        LinkedList<String> errors = new LinkedList<String>();
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String defaultMessage = fieldError.getDefaultMessage();
                errors.add(defaultMessage);
            }
        }
        return errors;
    }
}
