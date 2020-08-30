package com.monoprogram.myblend.presentation.top.blend.amount

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class AmountConfirmAdapter internal constructor(
    private var herbInfo: ArrayList<Herb>,
    private var itemValues: ArrayList<Float>
) :
    RecyclerView.Adapter<AmountConfirmAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageView: ImageView = v.findViewById(R.id.image_herb)
        var textView: TextView = v.findViewById(R.id.text_amount)
        var herbName: TextView = v.findViewById(R.id.text_herb_name)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.amount_blend_item, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(
            view
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(herbInfo[position].imageId)
        val inValue = String.format("%.1f", itemValues[position])
        holder.textView.text = inValue + "g"
        holder.herbName.text = herbInfo[position].herbName
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return herbInfo.size
    }

    fun updateValue(update: ArrayList<Float>) {
        itemValues = update
        notifyDataSetChanged()
    }

}