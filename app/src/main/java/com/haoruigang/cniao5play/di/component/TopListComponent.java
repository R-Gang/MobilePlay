package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.TopListModule;
import com.haoruigang.cniao5play.ui.fragment.TopListFragment;

import dagger.Component;

@FragmentScope
@Component(modules = TopListModule.class, dependencies = AppComponent.class)
public interface TopListComponent {

    void inject(TopListFragment fragment);

}
