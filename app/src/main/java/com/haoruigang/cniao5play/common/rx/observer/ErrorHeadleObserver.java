package com.haoruigang.cniao5play.common.rx.observer;

import com.google.gson.JsonParseException;
import com.haoruigang.cniao5play.common.exception.ApiException;
import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.exception.HttpException;

import java.net.SocketException;
import java.net.SocketTimeoutException;


public abstract class ErrorHeadleObserver<T> extends BaseObserver<T> {

    @Override
    public void onError(Throwable e) {

        BaseException exception = new BaseException();

        if (e instanceof ApiException) {// ApiException
            exception.setCode(((ApiException) e).getCode());
        } else if (e instanceof SocketException) {// SocketException
            exception.setCode(BaseException.SCOKET_ERROR);
        } else if (e instanceof SocketTimeoutException) {//SocketTimeoutException
            exception.setCode(BaseException.SCOKET_TIMEOUT_ERROR);
        } else if (e instanceof HttpException) {// HttpException
            exception.setCode(((HttpException) e).getCode());
        } else if (e instanceof JsonParseException) {//JsonParseException
            exception.setCode(BaseException.JSON_ERROR);
        } else {
            exception.setCode(BaseException.UNKOWN_ERROR);
        }

    }

}
