package com.eneskkoc.android.di

import android.app.Application
import com.eneskkoc.android.AndroidApp
import com.eneskkoc.android.data.service.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [AndroidSupportInjectionModule::class,
        Bindings::class,
        NetworkModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<AndroidApp> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder
        fun build(): AppComponent
    }
}