package com.haoruigang.cniao5play.common.rx.observer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.ui.activity.LoginActivity;

public abstract class ErrorHeadleObserver<T> extends BaseObserver<T> {

    Context mContext;
    RxErrorHandler rxErrorHandler;

    public ErrorHeadleObserver(Context context) {
        this.mContext = context;
        rxErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = rxErrorHandler.handleError(e);
        if (exception == null) {
            e.printStackTrace();
            Log.d("ErrorHeadleObserver", e.getMessage());
        } else {
            rxErrorHandler.showErrorMessage(exception);
            if (exception.getCode() == BaseException.ERROR_TOKEN) {
                // token 失效跳转至登录界面
                toLogin();
            }
        }
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

}
