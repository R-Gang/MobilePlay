package com.haoruigang.cniao5play.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static class SingletonHolder {
        static HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static final int DEFAULT_MILLISECONDS = 60000; //默认的超时时间

    private OkHttpClient getOkHttpClient() {
        // log 用拦截器
        //
        return new OkHttpClient.Builder()
                // 连接超时时间设置
                .connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                // 写入超时时间设置
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .build();
    }

    public Retrofit getRetrofit() {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//自动转换JSON需引入依赖 com.squareup.retrofit2:converter-gson
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//与RxJava2结合使用
                .client(getOkHttpClient())
                .build();
        return retrofit2;
    }

}
