package com.nhsoft.module.demo.data.exception;

public enum DemoError {

    DEMO_ERROR(10000, "DEMO错误"),
    USER_MISSING_ERROR(10001, "用户不存在"),
    AUTHORITY_FORBIDDEN_ERROR(10002,"没有权限");

    private final Integer code;
    private final String describe;

    DemoError(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
