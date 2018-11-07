package com.haoruigang.cniao5play.common.exception;

public class ApiException extends BaseException {

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }

}
