package com.haoruigang.cniao5play.common.rx.observer;

import android.content.Context;

import com.haoruigang.cniao5play.common.exception.BaseException;
import com.haoruigang.cniao5play.common.rx.ProgressDialogHandler;
import com.haoruigang.cniao5play.ui.BaseView;

import io.reactivex.disposables.Disposable;

public abstract class ProgressObserver<T> extends ErrorHeadleObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {

    BaseView mView;

    protected ProgressObserver(Context context, BaseView view) {
        super(context);
        this.mView = view;
    }

    //默认显示进度条
    protected boolean isShowProgress() {
        return true;
    }

    @Override
    public void onCancelProgress() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowProgress())
            mView.showLoading();
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = rxErrorHandler.handleError(e);
        if (isShowProgress())
            mView.showError(exception.getDisplayMessage());
    }

    @Override
    public void onComplete() {
        if (isShowProgress())
            mView.dismissLoading();
    }

}
