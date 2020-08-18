package com.monoprogram.myblend.presentation.top.blend.top

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Blend


class DefaultRecipeAdapter internal constructor(
    private var blendInfo: List<Blend>
) :
    RecyclerView.Adapter<DefaultRecipeAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var blendName: TextView = v.findViewById(R.id.text_blend_name)
        var herbName: TextView = v.findViewById(R.id.text_herb_name)
        var defaultBlend: ConstraintLayout = v.findViewById(R.id.layout_default_blend)
        var defaultImage: ImageView = v.findViewById(R.id.image_default)
        var description: TextView = v.findViewById(R.id.text_default_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.default_blend_item, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.blendName.text = blendInfo[position].blendName
        holder.herbName.text = blendInfo[position].herbName
        holder.description.text = blendInfo[position].blendDescription
        holder.defaultImage.setImageResource(blendInfo[position].blendImage)

        holder.defaultBlend.setOnClickListener {
            listener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return blendInfo.size
    }

    //インターフェースの作成
    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}