package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.CategoryModule;
import com.haoruigang.cniao5play.di.module.LoginModule;
import com.haoruigang.cniao5play.ui.activity.LoginActivity;
import com.haoruigang.cniao5play.ui.fragment.CategoryFragment;

import dagger.Component;

@FragmentScope
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {

    void inject(CategoryFragment fragment);

}
