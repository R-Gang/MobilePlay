package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.ui.BaseView;

import io.reactivex.Observable;

public interface LoginContract {

    interface ILoginModel {

        Observable<BaseBean<LoginBean>> login(String phone, String pwd);

    }

    interface LoginView extends BaseView {

        void checkPhoneError(String msg);

        void checkPhoneSuccess(String msg);

        void loginSuccess(LoginBean loginBean);

    }

}
