package com.mindorks.example.android_dagger2_example;

import android.app.Application;
import android.content.Context;

import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.di.component.ApplicationComponent;
import com.mindorks.example.android_dagger2_example.di.component.DaggerApplicationComponent;
import com.mindorks.example.android_dagger2_example.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by janisharali on 25/12/16.
 */

/*
* DemoApplication 에 사용될 dependencies 를 정의 내립니다.
*
* DemoApplication : @Inject DataManager 1개만 존재함
* 1. @Inject public DataManager(@ApplicationContext Context, DbHelper, SharedPrefsHelper)
* 2. 상세 설명
*    (1) Context : ApplicationContext @Qualifier
*    (2) DbHeler : @ApplicationContext Context, @DatabaseInfo String, @DatabaseInfo Integer
*    (3) SharedPrefsHelper : SharedPreferences
*
* 3. 합집합 = {Context, dbName, dbVersion, SharedPreferences}
*
* --> 이런 dependencies 를 ApplicationModule 을 통해서 제공합니다.
*
*
*
*
*
*
*
* */

public class DemoApplication extends Application {

    // Application has (ApplicationComponent): Dependency 주입을 위한 명세
    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    /*
    * Dependencies 를 주입하기 위한 코드입니다.
    *
    * DaggerApplicationComponent : Dagger2 에 의해서 자동생성된 클래서 (<- ApplicationComponent)
    * .___Modulee(new ___Module(this)) : 의존성을 부여해야하는 modules 를 지정합니다.
    *
    *
    * */
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                                    .applicationModule(new ApplicationModule(this))
                                    .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
