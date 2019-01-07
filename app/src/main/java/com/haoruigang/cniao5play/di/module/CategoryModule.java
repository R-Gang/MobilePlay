package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;

import com.haoruigang.cniao5play.data.CategoryModel;
import com.haoruigang.cniao5play.data.LoginModel;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.CategoryContract;
import com.haoruigang.cniao5play.presenter.contract.LoginContract;
import com.haoruigang.cniao5play.ui.activity.LoginActivity;
import com.haoruigang.cniao5play.ui.fragment.CategoryFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryModule {

    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view) {
        this.mView = view;
    }

    @Provides
    public CategoryContract.CategoryView provideView() {
        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel provideModule(ApiService mApiService) {
        return new CategoryModel(mApiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(CategoryContract.CategoryView view) {
        return new ProgressDialog(((CategoryFragment) view).getActivity());
    }

}
