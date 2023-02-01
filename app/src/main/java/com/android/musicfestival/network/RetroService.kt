package com.android.musicfestival.network

import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {

    @GET("api/v1/festivals")
    fun getBookListFromApi(): Observable<ArrayList<MusicFestivalItem>>
}