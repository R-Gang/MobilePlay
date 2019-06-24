package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.AppManagerModule;
import com.haoruigang.cniao5play.ui.fragment.AppManagerFragment;

import dagger.Component;

@FragmentScope
@Component(modules = AppManagerModule.class, dependencies = AppComponent.class)
public interface AppManagerComponent {

    void inject(AppManagerFragment fragment);

}
