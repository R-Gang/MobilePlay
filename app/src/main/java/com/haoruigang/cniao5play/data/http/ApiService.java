package com.haoruigang.cniao5play.data.http;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.bean.requestbean.LoginRequestBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    @GET("featured")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);// p={"page":0}

    @GET("index")
    Observable<BaseBean<PageBean<AppInfo>>> index();

    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);// {"page":0}

    @POST("login")
    Observable<BaseBean> login(@Body LoginRequestBean bean);
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
}
