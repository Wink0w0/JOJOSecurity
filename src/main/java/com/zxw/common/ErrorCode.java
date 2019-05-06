package com.zxw.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/28  21:17.
 * @Modified by:
 */
public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);

    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
