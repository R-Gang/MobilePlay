package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ErrorHeadleObserver;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.common.util.VerificationUtils;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {

    @Inject
    LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void login(String phone, String pwd) {
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mRootView.checkPhoneError("手机号码格式错误");
            return;
        } else if (!VerificationUtils.matcherPassword(phone)) {
            mRootView.checkPhoneError("密码长度需大于六位");
            return;
        } else {
            mRootView.checkPhoneSuccess("");
        }
        mModel.login(phone, pwd)
                .compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHeadleObserver<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mRootView.loginSuccess(loginBean);
                        saveUser(loginBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveUser(LoginBean bean) {
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getUser());
    }

}
