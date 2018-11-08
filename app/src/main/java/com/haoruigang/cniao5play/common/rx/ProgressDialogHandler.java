package com.haoruigang.cniao5play.common.rx;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;

import com.haoruigang.cniao5play.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 0;

    //    private ProgressDialog mProgressDialog;// 默认的dialog
    private SweetAlertDialog mProgressDialog;

    private Context mContext;
    private boolean cancelable;
    private OnProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context mContext,
                                 boolean cancelable,
                                 OnProgressCancelListener mProgressCancelListener) {
        this.mContext = mContext;
        this.cancelable = cancelable;
        this.mProgressCancelListener = mProgressCancelListener;
        initProgressDialog();
    }

//    private void initProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(mContext);
//            mProgressDialog.setMessage("loading......");
//            mProgressDialog.setCancelable(cancelable);
//            if (cancelable) {
//                mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
//                        "关闭", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (mProgressCancelListener != null) {
//                                    mProgressCancelListener.onCancelProgress();
//                                }
//                            }
//                        });
//            }
//        }
//    }

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            mProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mProgressDialog.setTitleText(mContext.getResources().getString(R.string.loading));
            mProgressDialog.setCancelable(cancelable);
            if (cancelable) {
                mProgressDialog.setCancelText(mContext.getResources().getString(R.string.close));
                mProgressDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        if (mProgressCancelListener != null) {
                            mProgressCancelListener.onCancelProgress();
                        }
                    }
                });
            }
        }
    }

    public void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDiolog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public interface OnProgressCancelListener {
        void onCancelProgress();
    }

}
