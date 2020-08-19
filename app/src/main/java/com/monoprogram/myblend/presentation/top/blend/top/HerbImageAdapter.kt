package com.monoprogram.myblend.presentation.top.blend.top

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class HerbImageAdapter internal constructor(
    private var herbInfo: List<Int>
) :
    RecyclerView.Adapter<HerbImageAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var herbImage: ImageView = v.findViewById(R.id.image_herb)
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
    }

    override fun getItemCount(): Int {
        return herbInfo.size
    }

}