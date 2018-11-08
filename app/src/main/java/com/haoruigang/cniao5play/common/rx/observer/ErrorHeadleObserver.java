package com.haoruigang.cniao5play.common.rx.observer;

import android.content.Context;
import android.util.Log;

import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;

public abstract class ErrorHeadleObserver<T> extends BaseObserver<T> {

    Context mContext;
    private RxErrorHandler rxErrorHandler;

    ErrorHeadleObserver(Context context) {
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
        }
    }

}
