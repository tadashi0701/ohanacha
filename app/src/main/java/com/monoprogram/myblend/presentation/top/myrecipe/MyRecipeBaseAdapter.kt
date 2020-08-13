package com.monoprogram.myblend.presentation.top.myrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class MyRecipeBaseAdapter internal constructor(
    private var herbInfo: List<Herb>
) :
    RecyclerView.Adapter<MyRecipeBaseAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageView: ImageView = v.findViewById(R.id.image_view)
        var textView: TextView = v.findViewById(R.id.text_view)
        var description: TextView = v.findViewById(R.id.text_herb_description)
        var selected: ImageView = v.findViewById(R.id.select_herb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_recipe_base_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(herbInfo[position].imageId)
        holder.textView.text = herbInfo[position].herbName
        holder.description.text = herbInfo[position].description

        holder.selected.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    override fun getItemCount(): Int {
        return herbInfo.size
    }

}