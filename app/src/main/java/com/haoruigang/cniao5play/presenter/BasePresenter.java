package com.haoruigang.cniao5play.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.haoruigang.cniao5play.ui.BaseView;

public class BasePresenter<M, V extends BaseView> {

    M mModel;
    V mRootView;

    Context mContext;

    BasePresenter(M m, V v) {
        this.mModel = m;
        this.mRootView = v;

        initContext();
    }

    private void initContext() {
        if (mRootView instanceof Fragment) {
            mContext = ((Fragment) mRootView).getActivity();
        } else {
            mContext = (Activity) mRootView;
        }
    }
}
