package com.monoprogram.myblend.presentations.top.HerbList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class HerbListAdapter internal constructor(
    private var herbInfo: List<Herb>
) :
    RecyclerView.Adapter<HerbListAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener
    private var selectList: ArrayList<String> = arrayListOf()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageView: ImageView = v.findViewById(R.id.image_view)
        var textView: TextView = v.findViewById(R.id.text_view)
        var selected: ImageView = v.findViewById(R.id.select_herb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.herb_select_item, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(herbInfo[position].imageId)
        holder.textView.text = herbInfo[position].herbName
        selectList.find { it == holder.textView.text.toString() }.also {
            holder.selected.isSelected = it != null
        }

        // ハーブ画像を押下を検知
        holder.imageView.setOnClickListener {
            listener.onHerbImageClickListener(position)
        }
        
        // ハーブ選択を検知
        holder.selected.setOnClickListener { select ->
            updateSelected(holder)
        }
    }

    override fun getItemCount(): Int {
        return herbInfo.size
    }

    private fun updateSelected(holder: ViewHolder) {
        holder.selected.isSelected = !holder.selected.isSelected
        selectList.find { it == holder.textView.text.toString() }.also {
            if (holder.selected.isSelected)
                selectList.add(holder.textView.text.toString())
            else
                selectList.remove(holder.textView.text.toString())
        }
        listener.onItemClickListener(selectList)
    }

    //インターフェースの作成
    interface OnItemClickListener {
        fun onItemClickListener(list: ArrayList<String>)
        fun onHerbImageClickListener(position: Int)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}