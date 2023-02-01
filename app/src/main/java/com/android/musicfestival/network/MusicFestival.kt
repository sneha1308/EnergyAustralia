package com.android.musicfestival.network


data class MusicFestivalItem( val bands: ArrayList<Band>,
                              val name: String)
data class Band(val name: String,
                val recordLabel: String)


