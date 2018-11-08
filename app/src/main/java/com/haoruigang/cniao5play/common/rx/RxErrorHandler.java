package com.haoruigang.cniao5play.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.haoruigang.cniao5play.common.exception.ApiException;
import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context context) {
        this.mContext = context;
    }

    public BaseException handleError(Throwable e) {
        BaseException exception = new BaseException();
        if (e instanceof ApiException) {// ApiException
            exception.setCode(((ApiException) e).getCode());
        } else if (e instanceof SocketException) {// SocketException
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (e instanceof SocketTimeoutException) {//SocketTimeoutException
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (e instanceof HttpException) {// HttpException
            exception.setCode(((HttpException) e).code());
        } else if (e instanceof JsonParseException) {//JsonParseException
            exception.setCode(BaseException.JSON_ERROR);
        } else {
            exception.setCode(BaseException.UNKOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        return exception;
    }

    public void showErrorMessage(BaseException e) {
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_LONG).show();
    }

}
