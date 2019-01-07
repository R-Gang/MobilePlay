package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;

import com.haoruigang.cniao5play.data.LoginModel;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;
import com.haoruigang.cniao5play.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view) {
        this.mView = view;
    }

    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModule(ApiService mApiService) {
        return new LoginModel(mApiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(LoginContract.LoginView view) {
        return new ProgressDialog(((LoginActivity) view));
    }

}
