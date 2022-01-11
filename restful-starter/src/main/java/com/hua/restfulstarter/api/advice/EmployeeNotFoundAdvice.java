package com.hua.restfulstarter.api.advice;

import com.hua.restfulstarter.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 定制当抛出 EmployeeNotFoundException 异常时，给接口返回的信息
 * advice会自动与相关异常关联
 * 关联了advice的异常，会被对应的advice handler处理，因此程序不会因为异常中断，而api也返回了定制的异常信息。
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

    @ResponseBody  // @ResponseBody表示直接呈现到响应体中。
    // @ExceptionHandler将通知配置为仅在引发EmployeeNotFoundException时响应。
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
