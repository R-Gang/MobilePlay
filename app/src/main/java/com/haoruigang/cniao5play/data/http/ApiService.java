package com.haoruigang.cniao5play.data.http;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.bean.requestbean.LoginRequestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //    @GET("featured2")
    @GET("featured")
    Observable<BaseBean<PageBean<AppInfoBean>>> getApps(@Query("page") int page);// p={"page":0}

    @GET("index")
    Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfoBean>>> topList(@Query("page") int page);// {"page":0}

    @GET("game")
    Observable<BaseBean<PageBean<AppInfoBean>>> games(@Query("page") int page);// {"page":0}

    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean bean);
//  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//    OkHttpClient client = new OkHttpClient();
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }


    @FormUrlEncoded
    @POST("login")
    void login2(@Field("phone") String phone);
//  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//    String post(String url, String json) throws IOException {
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.addEncoded("phone","");
//        body =builder.build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    @GET("category")
    Observable<BaseBean<List<CategoryBean>>> category();

    @GET("category/featured/{category_id}")
    Observable<BaseBean<PageBean<AppInfoBean>>> getFeaturedByCategory(@Path("category_id") int category_id, @Query("page") int page);

    @GET("category/toplist/{category_id}")
    Observable<BaseBean<PageBean<AppInfoBean>>> getTopListByCategory(@Path("category_id") int category_id, @Query("page") int page);

    @GET("category/newlist/{category_id}")
    Observable<BaseBean<PageBean<AppInfoBean>>> getNewListByCategory(@Path("category_id") int category_id, @Query("page") int page);

    @GET("app/{id}")
    Observable<BaseBean<AppInfoBean>> getAppDetail(@Path("id") int id);

    @GET("apps/updateinfo")
    Observable<BaseBean<List<AppInfoBean>>> getAppsUpdateinfo(@Query("packageName") String packageName, @Query("versionCode") String versionCode);
}
