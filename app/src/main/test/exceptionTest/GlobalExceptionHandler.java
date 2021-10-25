package main.test.exceptionTest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ReturnResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ReturnResult.failure(e.getErrorCode());
        }
        return ReturnResult.failure(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ReturnResult handle(Exception e) {

        if (e instanceof ArithmeticException){
            return ReturnResult.failure("123");
        }

        return ReturnResult.failure("系统异常");
    }
}

