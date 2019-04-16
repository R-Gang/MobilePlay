package com.haoruigang.cniao5play.common.util;

import android.app.Activity;
import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.fragment.app.FragmentActivity;
import io.reactivex.Observable;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.common.util
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class PermissionUtil {

    public static Observable<Boolean> requestPermisson(Context activity, String permission) {
        RxPermissions rxPermissions = new RxPermissions((FragmentActivity) activity);
        return rxPermissions.request(permission);
    }


}
