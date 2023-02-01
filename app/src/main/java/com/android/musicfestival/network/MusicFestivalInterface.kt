package com.android.musicfestival.network

import android.accounts.NetworkErrorException

interface MusicFestivalInterface {
    fun onError(e: Error, t:NetworkErrorException, msg: String)
}