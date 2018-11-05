package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.ui.BaseView;

public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mRootView;

    public BasePresenter(M m, V v) {
        this.mModel = m;
        this.mRootView = v;
    }
}
