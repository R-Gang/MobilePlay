package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.LoginModule;
import com.haoruigang.cniao5play.ui.activity.LoginActivity;
import com.haoruigang.cniao5play.ui.fragment.GamesFragment;
import com.haoruigang.cniao5play.ui.fragment.TopListFragment;

import dagger.Component;

@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
