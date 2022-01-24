package com.eneskkoc.android.data

import com.eneskkoc.android.data.model.comingmodel.Coming
import com.eneskkoc.android.data.model.moviemodel.Movie
import com.eneskkoc.android.data.model.nowmodel.Now
import com.eneskkoc.android.data.model.searchmodel.Search
import com.eneskkoc.android.data.model.similarmodel.Similar
import com.eneskkoc.android.data.service.*
import com.eneskkoc.android.di.PerApplication
import com.eneskkoc.android.util.Constants
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class AppDataManager @Inject constructor(private val comingApi: ComingApi, private val movieApi: MovieApi,private val nowApi: NowApi,private val similarApi: SimilarApi,private val searchApi: SearchApi) {

    fun api() : Observable<Coming> {
        return comingApi.getComing(Constants.API_KEY,Constants.Languae)
    }
    fun movieApi(movie_id:Int) : Observable<Movie> {
        return movieApi.getMovie(movie_id,Constants.API_KEY,Constants.Languae)
    }
    fun nowApi() : Observable<Now> {
        return nowApi.getNow(Constants.API_KEY,Constants.Languae)
    }

    fun similarApi(id:Int) : Observable<Similar> {
        return similarApi.getSimilar(id,Constants.API_KEY,Constants.Languae)
    }
    fun searchApi(query:String) : Observable<Search> {
        return searchApi.getSearch(Constants.API_KEY,Constants.Languae,query)
    }

}