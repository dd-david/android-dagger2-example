package com.mindorks.example.android_dagger2_example.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.mindorks.example.android_dagger2_example.di.ApplicationContext;
import com.mindorks.example.android_dagger2_example.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by janisharali on 25/12/16.
 */


/*
* Module Class
*   1. dependency 제공을 위해서는 Module 클래스를 선언해야 합니다
*   2. @Module annotation 을 사용합니다
*   3. Module 클래스 내부에 @Provides annotation 으로 셋업된, provider method 가 있습니다
*
* */
@Module
public class ApplicationModule {

    private final Application mApplication;

    /*
    * Constructor 에서 Application 객체를 파라미터로 받습니다.
    * 이 객체는 다른 dependencies 주입에서 사용됩니다.
    *
    * [참조] ApplicationComponent 를 확인합니다.
    * */
    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
