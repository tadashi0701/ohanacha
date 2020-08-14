package com.monoprogram.myblend.presentation.top.blend.top

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Blend


class MyRecipeAdapter internal constructor(
    private var blendInfo: List<Blend>
) :
    RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var blendName: TextView = v.findViewById(R.id.text_blend_name)
        var herbName: TextView = v.findViewById(R.id.text_herb_name)
        var myBlend: ConstraintLayout = v.findViewById(R.id.layout_my_blend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_blend_item, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.blendName.text = blendInfo[position].blendName
        holder.herbName.text = blendInfo[position].herbName

        holder.myBlend.setOnClickListener {
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