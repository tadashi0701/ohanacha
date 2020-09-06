package com.monoprogram.myblend.presentations.top.MyBlend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Blend


class MyRecipeAdapter internal constructor(
    private var blendInfo: List<Blend>
) :
    RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener
    private val herbDao = Application.database.herbDao()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var blendName: TextView = v.findViewById(R.id.text_blend_name)
        var myBlend: ConstraintLayout = v.findViewById(R.id.layout_my_blend)
        var herbImage: RecyclerView = v.findViewById(R.id.my_blend_recycler_view)
        var deleteBlend: ImageView = v.findViewById(R.id.delete_blend)
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

        // ブレンドしたハーブを表示する
        val image: ArrayList<Int> = arrayListOf()
        blendInfo[position].herbImageId.split(",").also { list ->
            list.forEach {
                image.add(it.toInt())
            }
        }
        val name: ArrayList<String> = arrayListOf()
        blendInfo[position].herbName.split(",").also { list ->
            list.forEach {
                name.add(it)
            }
        }
        val herbImageAdapter = HerbImageAdapter(image, name)
        holder.herbImage.let {
            it.layoutManager =
                LinearLayoutManager(Application.instance, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = herbImageAdapter
        }

        // ブレンドしたハーブの詳細を表示する
        holder.myBlend.setOnClickListener {
            listener.onItemClickListener(position)
        }

        // ブレンドしたハーブを削除する
        holder.deleteBlend.setOnClickListener {
            listener.onItemDeleteClickListener(position)
        }

    }

    override fun getItemCount(): Int {
        return blendInfo.size
    }

    //インターフェースの作成
    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
        fun onItemDeleteClickListener(position: Int)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}