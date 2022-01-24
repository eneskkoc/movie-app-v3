package com.eneskkoc.android.data.service

import com.eneskkoc.android.data.model.searchmodel.Search
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("movie")
    fun getSearch(

        @Query("api_key") api_key: String,
        @Query("language") language:String,
        @Query("query") query: String


        ): Observable<Search>
}