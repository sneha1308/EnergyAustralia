package com.android.musicfestival.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.musicfestival.network.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {
    var musicFestivalList: MutableLiveData<ArrayList<MusicFestivalItem>> = MutableLiveData()
    var error: Throwable = Throwable()

    fun getBookListObserver(): MutableLiveData<ArrayList<MusicFestivalItem>> {
        return musicFestivalList
    }

    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getBookListFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx(): Observer<ArrayList<MusicFestivalItem>> {
        return object : Observer<ArrayList<MusicFestivalItem>> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                error = e
                musicFestivalList.postValue(null)

            }

            override fun onNext(t: ArrayList<MusicFestivalItem>) {
                musicFestivalList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }
}