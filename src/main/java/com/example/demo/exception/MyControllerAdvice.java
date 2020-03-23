package com.example.demo.exception;

import com.nhsoft.provider.common.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response errorHandler(Exception e) {
        return Response.error(500, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = DemoException.class)
    public Response myErrorHandler(DemoException e) {
        return Response.error(e.getCode(), e.getMessage());
    }
}
