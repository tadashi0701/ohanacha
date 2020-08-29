package com.monoprogram.myblend.presentation.top.blend.top.MyBlend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R


class HerbImageAdapter internal constructor(
    private var herbInfo: List<Int>,
    private var herbName: List<String>
) :
    RecyclerView.Adapter<HerbImageAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var herbImage: ImageView = v.findViewById(R.id.image_herb)
        var herbName: TextView = v.findViewById(R.id.text_herb_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.herb_image_item, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.herbImage.setImageResource(herbInfo[position])
        holder.herbName.text = herbName[position]
    }

    override fun getItemCount(): Int {
        return herbInfo.size
    }

}