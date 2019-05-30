package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.bean.requestbean.LoginRequestBean;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        return apiService.login(LoginRequestBean.builder()
                .phone(phone).password(pwd)
                .build());
    }
}
