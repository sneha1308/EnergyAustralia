package com.android.musicfestival

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.musicfestival.network.*
import com.android.musicfestival.viewmodel.MainActivityViewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    var recordLabels = TreeMap<String, MutableMap<String, MutableList<String>>>()
    var festivals: ArrayList<MusicFestivalItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadAPIData()
    }


    private fun loadAPIData() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObserver().observe(this, Observer<ArrayList<MusicFestivalItem>> { it ->
            if (it != null) {
                festivals = it
                iterateFestivals()
            } else {
                if (viewModel.error.message.equals(resources.getString(R.string.too_many_requests)) || viewModel.error.message.equals(
                        resources.getString(R.string.error_message)
                    )
                ) {
                    viewModel.makeApiCall()
                }
            }
        })
        viewModel.makeApiCall()
    }

    private fun iterateFestivals() {
        this.recordLabels.clear()
        if (this.festivals.size == 0) {
            return
        }
        this.festivals.forEach {
            val bands = it.bands
            bands.forEach { band ->
                println(festivals)
                mapRecordLabelsWithBandNameAndFestivalName(band, it.name)
            }
        }
        val gson = GsonBuilder().setPrettyPrinting().create()
        tvMusicFestival.text = gson.toJson(recordLabels).toString()
    }

    private fun mapRecordLabelsWithBandNameAndFestivalName(band: Band, festival: String) {
        val label = band.recordLabel
        if (label.isNullOrEmpty() || festival.isNullOrEmpty()) {
            return
        }
        if (recordLabels.containsKey(label)) {
            val bandName = recordLabels.get(label)
            if (bandName!!.containsKey(band.name)) {
                bandName.get(band.name)?.add(festival)
            } else {
                bandName.put(band.name, mutableListOf())
                val fest = bandName.get(band.name)
                fest?.add(festival)
            }
        } else {
            recordLabels.put(label, TreeMap<String, MutableList<String>>())
            val bandName = recordLabels.get(label)
            if (bandName!!.containsKey(band.name)) {
                bandName.get(band.name)?.add(festival)
            } else {
                bandName.put(band.name, mutableListOf())
                val fest = bandName.get(band.name)
                fest?.add(festival)
            }
        }
    }
}