package com.haoruigang.cniao5play.common.http;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 公共参数拦截器
 */
public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Context mContext;
    private Gson mGson;

    public CommonParamsInterceptor(Context context, Gson gson) {
        this.mContext = context;
        this.mGson = gson;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();// http://112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0}

        try {
            String method = request.method();

            String imei = DeviceUtils.getIMEI(mContext);
            String model = DeviceUtils.getModel();
            String language = DeviceUtils.getLanguage();
            String os = DeviceUtils.getBuildVersionIncremental();
            String resolution = DeviceUtils.getScreenDisplayID(mContext);
            String sdk = DeviceUtils.getBuildVersionSDK() + "";
            String densityScaleFactor = mContext.getResources().getDisplayMetrics().density + "";

            Log.i("IMEI", imei);
            Log.i("Model", model);
            Log.i("LANGUAGE", language);
            Log.i("OS", os);
            Log.i("RESOLUTION", resolution);
            Log.i("SDK", sdk);
            Log.i("DENSITY_SCALE_FACTOR", densityScaleFactor);

            HashMap<String, Object> commomParamesMap = new HashMap<>();
            commomParamesMap.put(Constant.IMEI, imei);
            commomParamesMap.put(Constant.MODEL, model);
            commomParamesMap.put(Constant.LANGUAGE, language);
            commomParamesMap.put(Constant.OS, os);
            commomParamesMap.put(Constant.RESOLUTION, resolution);
            commomParamesMap.put(Constant.SDK, sdk);
            commomParamesMap.put(Constant.DENSITY_SCALE_FACTOR, densityScaleFactor);

            if (method.equals("GET")) {

                HttpUrl httpUrl = request.url();

                Set<String> paramNames = httpUrl.queryParameterNames();
                HashMap<String, Object> rootMap = new HashMap<>();
                for (String key : paramNames) {
                    if (Constant.PARAM.equals(key)) {// p={'page':0}&page=0&number=1 格式的解析方式
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if (oldParamJson != null) {//非空验证
                            HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class);//原始参数
                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {
                                    rootMap.put(entry.getKey(), entry.getValue());
                                }
                            }
                        }
                    } else {
                        rootMap.put(key, httpUrl.queryParameter(key));
                    }
                }

                rootMap.put("publicParams", commomParamesMap);//重新组装公共参数

                String newJsonParams = mGson.toJson(rootMap);// {'page':0,'publicParams':{'imei':xxx,'model':xxx,...}}

                String url = httpUrl.toString();

                int index = url.indexOf("?");
                if (index > 0) {// ? 存在,说明带有参数
                    url = url.substring(0, index);
                }
                // http://112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0,'publicParams':{'imei':xxx,'model':xxx,...}}
                url = url + "?" + Constant.PARAM + "=" + newJsonParams;

                request = request.newBuilder().url(url).build();//重新构建路径

            } else if (method.equals("POST")) {

                RequestBody body = request.body();

                HashMap<String, Object> rootMap = new HashMap<>();
                if (body instanceof FormBody) {// form 表单形式
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                } else {// json 格式
                    Buffer buffer = new Buffer();

                    body.writeTo(buffer);

                    String oldJsonParams = buffer.readUtf8();

                    rootMap = mGson.fromJson(oldJsonParams, HashMap.class);//原始参数
                    rootMap.put("publicParams", commomParamesMap);//重新组装公共参数
                    String newJsonParams = mGson.toJson(rootMap);// {'page':0,'publicParams':{'imei':xxx,'model':xxx,...}}

                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                }

            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chain.proceed(request);// 重新发起请求
    }
}
