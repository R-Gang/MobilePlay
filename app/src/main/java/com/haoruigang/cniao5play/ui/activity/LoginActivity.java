package com.haoruigang.cniao5play.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eftimoff.androipathview.PathView;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.common.util.DeviceUtils;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerLoginComponent;
import com.haoruigang.cniao5play.di.module.LoginModule;
import com.haoruigang.cniao5play.presenter.LoginPresenter;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

/**
 * 登录页
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    private final int READ_PHONE_STATE_CODE = 1000;

    @BindView(R.id.pathView)
    PathView pathView;
    @BindView(R.id.iv_mobile)
    ImageView ivMobile;
    @BindView(R.id.view_mobile_wrapper)
    TextInputLayout viewMobileWrapper;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        pathView.getPathAnimator()
                .delay(1000)//停留延迟
                .duration(6000)//持续时间
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        ivMobile.setVisibility(View.VISIBLE);
                    }
                })
                .start();

        initView();
    }

    @SuppressLint("CheckResult")
    private void initView() {
        Observable.combineLatest(
                RxTextView.textChanges(etMobile),
                RxTextView.textChanges(etPassword),
                new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence mobile, CharSequence pwd) {
                        return isPhoneValid(mobile.toString()) && isPasswordValid(pwd.toString());
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        btnLogin.setEnabled(aBoolean);
                    }
                });

        RxView.clicks(btnLogin).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) {
                mPresenter.login(etMobile.getText().toString().trim(),
                        etPassword.getText().toString().trim());
            }
        });
    }

    private boolean isPasswordValid(String s) {
        return s.length() > 6;
    }

    private Boolean isPhoneValid(String s) {
        return s.length() == 11;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String imes = DeviceUtils.getIMEI(this);
                Toast.makeText(this, imes, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void checkPhoneError(String msg) {
        viewMobileWrapper.setError(msg);
    }

    @Override
    public void checkPhoneSuccess(String msg) {
        viewMobileWrapper.setError(msg);
        viewMobileWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) { // 检查权限,没有授权
            // 授权
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
        } else {
            // 授权成功
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}
