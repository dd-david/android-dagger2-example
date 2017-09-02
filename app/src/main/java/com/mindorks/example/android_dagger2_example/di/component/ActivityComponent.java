package com.mindorks.example.android_dagger2_example.di.component;

import com.mindorks.example.android_dagger2_example.MainActivity;
import com.mindorks.example.android_dagger2_example.di.PerActivity;
import com.mindorks.example.android_dagger2_example.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by janisharali on 08/12/16.
 */

/*
* @PerActivity:
*    -> @Component(modules = ActivityModule.class) 에서 명세하고 있는
*       @Provides provideContext(), @Provides provideActivity() 의 의존성을 부여할때,
*       매 Activity 가 생성될 때마다 새롭게 생성함을 의미합니다.
*
* @Component := interface
* DaggerComponent.builder() := concrete class
* -------------------------------------------> concrete class # inject(concrete class)
*                                              를 통해서 dependency graph 에 의존성을 주입
*
* */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
