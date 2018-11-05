package com.haoruigang.cniao5play.di.module;

import com.haoruigang.cniao5play.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    private static final int DEFAULT_MILLISECONDS = 60000; //默认的超时时间


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
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

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//自动转换JSON需引入依赖 com.squareup.retrofit2:converter-gson
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//与RxJava2结合使用
                .client(okHttpClient)
                .build();
        return retrofit2;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
