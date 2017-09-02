package com.mindorks.example.android_dagger2_example.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by janisharali on 25/12/16.
 */

/*
* Custom Annotation 을 만듭니다
* 1. Custom Annotation 을 정의하는 목적:
*
*
* @Qualifier
* 1. javax inject 패키지에 포함
* 2. qualify(자격을 주다) dependency 에 사용됩니다
* 3. 같은 타입이지만, 다른 객체(instance)를 구분 해줍니다
*
* (ex) 특정 class 에서, Application Context 와 Activity Context 를 모두 요청할 수 있습니다
*      두 요청 모두 Context 타입을 나타내는데, 어떻게 구분할 수 있을까요?
*      Dagger2 에서는 어떤 변수(@Inject)에 <- 어떤 객체가 주입되어야 할지(@Provides) 결정하게 됩니다
*      그래서 개발자는 명시적으로 이들 관계를 정의해줄 필요가 있습니다
*
* (ex) 이 프로젝트에는 3개의 Context 가 존재합니다: Application Context, Activity Context, Context
*
*
* @Named
* 1. @Qualifier 의 대체제 입니다 (in dagger2)
* 2. @Named 는 @Qualifier 와 함께 사용됩니다 -> 사용 예는 블로그 뒤에서 하께 다뤄봅니다
* */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
