package com.example.demo.exception;

public class DemoException extends RuntimeException {

    private Integer code;

    public DemoException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
