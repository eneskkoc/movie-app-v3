package com.eneskkoc.android.data.service

import com.eneskkoc.android.data.model.nowmodel.Now
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NowApi {

    @GET("now_playing")
    fun getNow(

        @Query("api_key") api_key: String,
        @Query("language") language:String,


        ): Observable<Now>
}