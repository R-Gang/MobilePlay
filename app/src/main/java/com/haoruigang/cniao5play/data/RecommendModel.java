package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.http.ApiService;
import com.haoruigang.cniao5play.http.HttpManager;

import retrofit2.Callback;

public class RecommendModel {

    public void getApps(Callback<PageBean<AppInfo>> callback) {
        HttpManager.getInstance().getRetrofit().create(ApiService.class)
                .getApps("{“page”:0}").enqueue(callback);
    }

}
