package com.mindorks.example.android_dagger2_example.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by janisharali on 08/12/16.
 */

/*
* @Scope
* 1. dependency 객체가 유지될(persist) 범위를 다룹니다
*
*
* */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

