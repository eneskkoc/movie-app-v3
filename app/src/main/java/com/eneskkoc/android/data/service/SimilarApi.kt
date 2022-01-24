package com.eneskkoc.android.data.service

import com.eneskkoc.android.data.model.moviemodel.Movie
import com.eneskkoc.android.data.model.similarmodel.Similar
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarApi {

    @GET("{movie_id}/similar")
    fun getSimilar(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key: String,
        @Query("language") language:String,

        ): Observable<Similar>

}