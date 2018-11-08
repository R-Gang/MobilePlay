package com.haoruigang.cniao5play.data.http;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.PageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    @GET("featured")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);
}
