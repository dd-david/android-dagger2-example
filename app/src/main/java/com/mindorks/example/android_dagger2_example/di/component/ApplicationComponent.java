package com.mindorks.example.android_dagger2_example.di.component;

import android.app.Application;
import android.content.Context;

import com.mindorks.example.android_dagger2_example.DemoApplication;
import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.data.DbHelper;
import com.mindorks.example.android_dagger2_example.data.SharedPrefsHelper;
import com.mindorks.example.android_dagger2_example.di.ApplicationContext;
import com.mindorks.example.android_dagger2_example.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by janisharali on 08/12/16.
 */

/*
* ___Component 는 Dagger2 에 의해서 만들어지는 interface 입니다.
* 1. @Component annotation 을 사용합니다.
* 2.
*
*
* */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    /*
    * TODO: inject(DemoApplication) 이란 메소드를 만든 이유는?
    *
    *
    *
    * */
    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();

}
