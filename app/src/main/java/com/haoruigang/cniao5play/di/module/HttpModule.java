package com.haoruigang.cniao5play.di.module;

import android.app.Application;
import android.net.SSLCertificateSocketFactory;

import com.google.gson.Gson;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.http.CommonParamsInterceptor;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    private static final int DEFAULT_MILLISECONDS = 60000; //默认的超时时间


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application, Gson gson) {
        /* log 用拦截器 */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // 开发模式记录整个body,否则只记录基本信息如返回200,http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //如果使用HTTPS,我们需要创建SSLSocketFactory,并设置到client
//        SSLSocketFactory sslSocketFactory = new SSLCertificateSocketFactory(DEFAULT_MILLISECONDS);
        return new OkHttpClient.Builder()
//                .addInterceptor(logging)
                .addInterceptor(new CommonParamsInterceptor(application, gson))
//                .socketFactory(sslSocketFactory)
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
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                //需引入依赖 com.squareup.retrofit2:converter-gson
                .addConverterFactory(GsonConverterFactory.create())//自动转换JSON
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//与RxJava2结合使用
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public RxErrorHandler provideRxErrorHander(Application application) {
        return new RxErrorHandler(application);
    }

}
