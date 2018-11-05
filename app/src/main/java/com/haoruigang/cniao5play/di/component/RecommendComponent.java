package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.di.FragmentScope;
import com.haoruigang.cniao5play.di.module.AppModule;
import com.haoruigang.cniao5play.di.module.RecommendModule;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import dagger.Component;

@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
