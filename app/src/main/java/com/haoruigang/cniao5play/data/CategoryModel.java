package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;

public class CategoryModel implements CategoryContract.ICategoryModel {

    private ApiService apiService;

    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<CategoryBean>>> category() {
        return apiService.category();
    }
}
