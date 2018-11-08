package com.haoruigang.cniao5play.common.rx.observer;

import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;

public abstract class ErrorHeadleObserver<T> extends BaseObserver<T> {

    private RxErrorHandler rxErrorHandler;

    protected ErrorHeadleObserver(RxErrorHandler rxErrorHandler) {
        this.rxErrorHandler = rxErrorHandler;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = rxErrorHandler.handleError(e);
        rxErrorHandler.showErrorMessage(exception);
    }

}
