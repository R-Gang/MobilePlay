package com.haoruigang.cniao5play.common.rx.observer;

import android.content.Context;

import com.haoruigang.cniao5play.common.rx.ProgressDialogHandler;

import io.reactivex.disposables.Disposable;

public abstract class ProgressDialogObserver<T> extends ErrorHeadleObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandle;
    private Disposable mDisposable;

    /**
     * @param context
     * @param isCancelDialog 设置该对话框是否可取消 true 可取消,false 不可取消
     */
    protected ProgressDialogObserver(Context context, boolean isCancelDialog) {
        super(context);
        mProgressDialogHandle = new ProgressDialogHandler(mContext, isCancelDialog, this);
    }

    //默认显示Dialog
    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
        mDisposable.dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (isShowProgressDialog())
            mProgressDialogHandle.showProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog())
            mProgressDialogHandle.dismissProgressDiolog();
    }

    @Override
    public void onComplete() {
        if (isShowProgressDialog())
            mProgressDialogHandle.dismissProgressDiolog();
    }

}
