package com.monoprogram.myblend.presentation.top.blend.myblend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class MyBlendAdapter internal constructor(
    private var herbInfo: List<Herb>
) :
    RecyclerView.Adapter<MyBlendAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageView: ImageView = v.findViewById(R.id.image_view)
        var textView: TextView = v.findViewById(R.id.text_view)
        var description: TextView = v.findViewById(R.id.text_herb_description)
        var selected: ImageView = v.findViewById(R.id.select_herb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_recipe_base_item, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(herbInfo[position].imageId)
        holder.textView.text = herbInfo[position].herbName
        holder.description.text = herbInfo[position].description

        holder.selected.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected) listener.onItemClickListener(
                holder.textView.text.toString(),
                it.isSelected
            )
        }
    }

    override fun getItemCount(): Int {
        return herbInfo.size
    }

    //インターフェースの作成
    interface OnItemClickListener {
        fun onItemClickListener(herbName: String, needsAdd: Boolean)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}