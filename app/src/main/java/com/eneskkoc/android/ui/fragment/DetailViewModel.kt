package com.eneskkoc.android.ui.fragment

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.eneskkoc.android.base.BaseViewModel
import com.eneskkoc.android.data.AppDataManager
import com.eneskkoc.android.data.model.similarmodel.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private var appDataManager: AppDataManager) : BaseViewModel() {

    val data = MutableLiveData<State>()

    //val progressVisible = ObservableField(false)
    var title: String? =null
    var rating:Double?=null
    var overview:String?=null
    var date:String?=null
    var similar:List<Result>?=null
    val image = ObservableField<String>()

    @SuppressLint("CheckResult")
    fun movie(movie_id:Int) {
        // progressVisible.set(true)
        appDataManager.movieApi(movie_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                title=it.original_title
                rating=it.vote_average
                overview=it.overview
                date=it.release_date
                image.set(it.poster_path)
                data.postValue(State.OnCompleted)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }
    @SuppressLint("CheckResult")
    fun similar(movie_id:Int) {
        // progressVisible.set(true)
        appDataManager.similarApi(movie_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                similar=it.results
                data.postValue(State.OnCompleted)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }
    sealed class State {
        object OnCompleted : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}