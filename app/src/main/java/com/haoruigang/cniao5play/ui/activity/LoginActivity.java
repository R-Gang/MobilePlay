package com.haoruigang.cniao5play.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.common.util.DeviceUtils;
import com.haoruigang.cniao5play.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LoginActivity extends BaseActivity {

    private final int READ_PHONE_STATE_CODE = 1000;

    @BindView(R.id.btn)
    Button btn;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) { // 检查权限,没有授权
                    // 授权
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
                } else {
                    // 授权成功
                    String imes = DeviceUtils.getIMEI(this);
                    Toast.makeText(this, imes, Toast.LENGTH_LONG).show();
                }
                break;
        }
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
}
