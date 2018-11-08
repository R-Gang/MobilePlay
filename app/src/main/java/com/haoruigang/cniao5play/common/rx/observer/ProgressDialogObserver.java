package com.haoruigang.cniao5play.common.rx.observer;

import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.ui.BaseView;

import io.reactivex.disposables.Disposable;

public abstract class ProgressDialogObserver<T> extends ErrorHeadleObserver<T> {

    private BaseView baseView;
//    private ProgressDialog mProgress;

    protected ProgressDialogObserver(BaseView view, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.baseView = view;
    }

    //默认显示Dialog
    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowDialog())
            showProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowDialog())
            dismissProgressDiolog();
    }

    @Override
    public void onComplete() {
        if (isShowDialog())
            dismissProgressDiolog();
    }

//    private void initProgressDiolog() {
//        if (mProgress == null) {
//            mProgress = new ProgressDialog(mContext);
//            mProgress.setMessage("loading......");
//        }
//    }

    private void showProgressDialog() {
//        initProgressDiolog();
        baseView.showLoading();
    }

    private void dismissProgressDiolog() {
//        if (mProgress != null && mProgress.isShowing()) {
//            mProgress.dismiss();
//        }
        baseView.dimissLoading();
    }


}
