package com.haoruigang.cniao5play.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eftimoff.androipathview.PathView;
import com.google.android.material.textfield.TextInputLayout;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.common.util.DeviceUtils;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerLoginComponent;
import com.haoruigang.cniao5play.di.module.LoginModule;
import com.haoruigang.cniao5play.presenter.LoginPresenter;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;
import com.haoruigang.cniao5play.ui.widget.LoadingButton;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 登录页
 * 课时4：在Android上使用SVG矢（读音:[shǐ]）量图
 * 课时5：在Android中使用iconfont图标
 * 课时6：自定义iconfont
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    private final int READ_PHONE_STATE_CODE = 1000;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
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
    LoadingButton btnLogin;

    @BindView(R.id.iv_telephone)
    ImageView ivTelephone;

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
        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );
        mToolBar.setTitle(R.string.login);
        mToolBar.setNavigationOnClickListener(v -> finish());

        //SVG矢量图
        Drawable drawable = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_telephone_outline)
                .color(Color.RED).sizeDp(36);
        ivTelephone.setImageDrawable(drawable);

        pathView.getPathAnimator()
                .delay(1000)//停留延迟
                .duration(6000)//持续时间
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(() -> ivMobile.setVisibility(View.VISIBLE))
                .start();

        initView();
    }

    @SuppressLint("CheckResult")
    private void initView() {
        Observable.combineLatest(
                RxTextView.textChanges(etMobile),
                RxTextView.textChanges(etPassword),
                (mobile, pwd) ->
                        isPhoneValid(mobile.toString()) && isPasswordValid(pwd.toString()))
                .subscribe(aBoolean ->
                        btnLogin.setEnabled(aBoolean));

        RxView.clicks(btnLogin).subscribe(unit ->
                mPresenter.login(etMobile.getText().toString().trim(),
                        etPassword.getText().toString().trim()));
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
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void showLoading() {
        btnLogin.showLoading();
    }

    @Override
    public void showError(String msg) {
        btnLogin.showButtonText();
    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();
    }
}
