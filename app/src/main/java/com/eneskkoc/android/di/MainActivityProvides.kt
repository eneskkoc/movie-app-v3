package com.eneskkoc.android.di

import com.eneskkoc.android.ui.fragment.DetailFragment
import com.eneskkoc.android.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvides {

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): DetailFragment

}
