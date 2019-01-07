package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel, CategoryContract.CategoryView> {

    @Inject
    CategoryPresenter(CategoryContract.ICategoryModel iCategoryModel, CategoryContract.CategoryView categoryView) {
        super(iCategoryModel, categoryView);
    }

    public void getAllCategory() {
        mModel.category()
                .compose(RxHttpResponseCompat.<List<CategoryBean>>compatResult())
                .subscribe(new ProgressObserver<List<CategoryBean>>(mContext, mRootView) {
                    @Override
                    public void onNext(List<CategoryBean> categoryBeans) {
                        mRootView.showData(categoryBeans);
                    }
                });
    }
}
