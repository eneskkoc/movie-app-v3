package com.eneskkoc.android.data.service

import com.eneskkoc.android.data.model.comingmodel.Coming
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ComingApi {

    @GET("upcoming")
    fun getComing(
        @Query("api_key") api_key: String,
        @Query("language") language:String

    ): Observable<Coming>

}
