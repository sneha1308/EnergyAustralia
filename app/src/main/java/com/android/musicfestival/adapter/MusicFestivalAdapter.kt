package com.android.musicfestival.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.musicfestival.R
import kotlinx.android.synthetic.main.item_music_festival.view.*
import java.util.TreeMap

class MusicFestivalAdapter : RecyclerView.Adapter<MusicFestivalAdapter.MyViewHolder>() {

    var recordLabels = TreeMap<String, Map<String, MutableList<String>>>()
    var count = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicFestivalAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_music_festival, parent, false)
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: MusicFestivalAdapter.MyViewHolder, position: Int) {
        //holder.bind(bookListData[position],position)

    }

    override fun getItemCount(): Int {
        return recordLabels.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvRecordLabel = view.tvRecordLabel
        var tvBand = view.tvBand
        var tvFestival = view.tvFestival
    }
}