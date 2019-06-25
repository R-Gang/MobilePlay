package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.AppInfoModule;
import com.haoruigang.cniao5play.ui.fragment.BaseAppInfoFragment;

import dagger.Component;

@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {

    void inject(BaseAppInfoFragment fragment);

}
