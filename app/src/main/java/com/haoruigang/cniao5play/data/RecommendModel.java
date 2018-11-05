package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.http.ApiService;

import retrofit2.Callback;

public class RecommendModel {

    public ApiService apiService;

    public RecommendModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback) {
        apiService.getApps("{“page”:0}").enqueue(callback);
    }

}
