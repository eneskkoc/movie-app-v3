package com.eneskkoc.android.data.service

import com.eneskkoc.android.di.PerApplication
import com.eneskkoc.android.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @PerApplication
    fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): ComingApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(ComingApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideMovieApiService(gson: Gson, okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(MovieApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideNowApiService(gson: Gson, okHttpClient: OkHttpClient): NowApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(NowApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideSimilarApiService(gson: Gson, okHttpClient: OkHttpClient): SimilarApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(SimilarApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideSearchApiService(gson: Gson, okHttpClient: OkHttpClient): SearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.SEARCH_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(SearchApi::class.java)
    }
}