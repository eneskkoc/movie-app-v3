package com.eneskkoc.android

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.eneskkoc.android.di.DaggerAppComponent

class AndroidApp: DaggerApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun applicationInjector(): AndroidInjector<out AndroidApp> {
        return DaggerAppComponent.builder().app(this).build()
    }
}