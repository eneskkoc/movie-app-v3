package com.eneskkoc.android.ui.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.denzcoskun.imageslider.models.SlideModel
import com.eneskkoc.android.base.BaseViewModel
import com.eneskkoc.android.data.AppDataManager
import com.eneskkoc.android.data.model.comingmodel.Result
import com.eneskkoc.android.data.model.nowmodel.NowResult
import com.eneskkoc.android.data.model.searchmodel.Search
import com.eneskkoc.android.data.model.searchmodel.SearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private var appDataManager: AppDataManager) : BaseViewModel() {
    val data = MutableLiveData<State>()
    var comResult: List<Result>? = null
    var nowResult: List<NowResult>? = null
    val imageList = ArrayList<SlideModel>()
    var id: Int? = null
    var list: Search? = null
    private var searhResult: List<SearchResult>? = null
    //val progressVisible = ObservableField(false)

    @SuppressLint("CheckResult")
    fun coming() {
        // progressVisible.set(true)
        appDataManager.api()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success

                comResult = it.results
                data.postValue(State.OnCompleted)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }

    @SuppressLint("CheckResult")
    fun now() {
        // progressVisible.set(true)
        appDataManager.nowApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success

                nowResult = it.results
                nowResult?.forEach { image ->
                    imageList.add(SlideModel("https://image.tmdb.org/t/p/w300" + image.poster_path, "" + image.title))
                }

                data.postValue(State.OnNow)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }
    @SuppressLint("CheckResult")
    fun nowId(position: Int) {
        // progressVisible.set(true)
        appDataManager.nowApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                id = it.results?.get(position)?.id

                data.postValue(State.OnNowDetail)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }

    @SuppressLint("CheckResult")
    fun search(query: String) {
        // progressVisible.set(true)
        appDataManager.searchApi(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                searhResult = it.results
                data.value = State.OnSearch(it.results)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }

    fun findSearchItem(title: String) = searhResult?.find { it.title == title }


    sealed class State {
        object OnCompleted : State()
        object OnNow : State()
        object OnNowDetail : State()
        data class OnSearch(val searchResult: List<SearchResult>?) : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}