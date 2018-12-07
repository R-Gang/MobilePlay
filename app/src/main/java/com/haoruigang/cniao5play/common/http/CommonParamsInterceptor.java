package com.haoruigang.cniao5play.common.http;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 公共参数拦截器
 */
public class CommonParamsInterceptor implements Interceptor {

    private Context mContext;
    private Gson mGson;

    public CommonParamsInterceptor(Context context, Gson gson) {
        this.mContext = context;
        this.mGson = gson;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String method = request.method();

        HashMap<String, Object> commomParamesMap = new HashMap<>();
        commomParamesMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        commomParamesMap.put(Constant.MODEL, DeviceUtils.getModel());
        commomParamesMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
        commomParamesMap.put(Constant.OS, DeviceUtils.getBuildVersionIncremental());
        commomParamesMap.put(Constant.RESOLUTION, DeviceUtils.getScreenDisplayID(mContext));
        commomParamesMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
        commomParamesMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");

        if (method.equals("GET")) {

            HttpUrl httpUrl = request.url();
            String oldParamJson = httpUrl.queryParameter(Constant.PARAM);

            HashMap rootMap = mGson.fromJson(oldParamJson, HashMap.class);//原始参数

            rootMap.put("publicParams", commomParamesMap);//重新组装公共参数

            String newJsonParams = mGson.toJson(rootMap);

            String url = httpUrl.toString();
            url = url.substring(0, url.indexOf("?")) + "?" + Constant.PARAM;

        } else if (method.equals("POST")) {

        }

        return null;
    }
}
