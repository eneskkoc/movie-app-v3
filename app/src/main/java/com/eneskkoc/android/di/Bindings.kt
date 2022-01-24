package com.eneskkoc.android.di

import com.eneskkoc.android.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityProvides::class])
    abstract fun bindMainActivity(): MainActivity
}