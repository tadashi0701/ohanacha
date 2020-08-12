package com.monoprogram.myblend.presentation.top.defaultrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R


class DefaultRecipeAdapter internal constructor(
    private var itemImages: ArrayList<Int>,
    private var itemNames: ArrayList<String>,
    private var itemValues: ArrayList<Int>
) :
    RecyclerView.Adapter<DefaultRecipeAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageView: ImageView = v.findViewById(R.id.image_view)
        var textView: TextView = v.findViewById(R.id.text_view)
        var seekBar: SeekBar = v.findViewById(R.id.seekbar)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_item, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageResource(itemImages[position])
        holder.textView.text = itemNames[position]
        holder.seekBar.setProgress(itemValues[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return itemNames.size
    }

}