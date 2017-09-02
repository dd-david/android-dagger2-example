package com.mindorks.example.android_dagger2_example;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.data.model.User;
import com.mindorks.example.android_dagger2_example.di.component.ActivityComponent;
import com.mindorks.example.android_dagger2_example.di.component.DaggerActivityComponent;
import com.mindorks.example.android_dagger2_example.di.module.ActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager mDataManager;

    private ActivityComponent activityComponent;

    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    public ActivityComponent getActivityComponent() {
        /*
        * 실제 my-component, ActivityComponent 설정은 다음과 같습니다
        * @PerActivity
        * @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
        * public interface ActivityComponent
        *
        * Component 의 Dependency Graph 를 생성하는 방법
        * 1. [DaggerComponentBuilder 생성] Dagger + (my-component) .builder()
        * 2. [modules 세팅]                .module(new module)
        * 3. [dependency 세팅]             .component(new component)
        * 4. [Component 생성]              .build()
        *
        * (상세-3) dependency 세팅을 할때,
        * .component(new component)
        *            -------------
        *            여기는 (상위) Component 이니깐, DaggerComponent.builder 를 통해서 concrete class 를 생성할 수 없음 (중복)
        *            그래서 해당 DaggerComponent.build 를 통해서 만들어낸 객체를 가져와서 주입할 수 있도록, static method 를 만들어 둔다.
        *
        * */
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()                   /* Dagger + my-component # builder() */
                    .activityModule(new ActivityModule(this))                       /* "modules" 에 포함되면      -> .module(new module) */
                    .applicationComponent(DemoApplication.get(this).getComponent()) /* "dependencies" 에 포함되면 -> .component(new component) */
                    .build();                                                       /* .build() -> my-component */


        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mTvUserInfo = (TextView) findViewById(R.id.tv_user_info);
        mTvAccessToken = (TextView) findViewById(R.id.tv_access_token);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createUser();
        getUser();
        mDataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543");

        String token = mDataManager.getAccessToken();
        if(token != null){
            mTvAccessToken.setText(token);
        }
    }

    private void createUser(){
        try {
            mDataManager.createUser(new User("Ali", "1367, Gurgaon, Haryana, India"));
        }catch (Exception e){e.printStackTrace();}
    }

    private void getUser(){
        try {
            User user = mDataManager.getUser(1L);
            mTvUserInfo.setText(user.toString());
        }catch (Exception e){e.printStackTrace();}
    }
}
