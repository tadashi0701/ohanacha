package com.monoprogram.myblend.presentations.top.MyBlend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Blend


class MyRecipeAdapter internal constructor(
    private val context: Context,
    private var blendInfo: List<Blend>
) :
    RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener
    private val herbDao = Application.database.herbDao()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var blendName: TextView = v.findViewById(R.id.text_blend_name)
        var herbImage: RecyclerView = v.findViewById(R.id.my_blend_recycler_view)
        var fbBlend: ImageButton = v.findViewById(R.id.btn_top)
        var fbDelete: ImageButton = v.findViewById(R.id.btn_delete)
        var fbShare: ImageButton = v.findViewById(R.id.btn_share)
        var fbEdit: ImageButton = v.findViewById(R.id.btn_edit)
        var isFabOpen: Boolean = false
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

        // ブレンド設定ボタン
        holder.fbBlend.setOnClickListener {
            if (holder.isFabOpen) {
                closeFabMenu(holder)
            } else {
                openFabMenu(holder)
            }
        }

        // ブレンドしたハーブを削除する
        holder.fbDelete.setOnClickListener {
            listener.onItemDeleteClickListener(position)
        }

        // ブレンドしたハーブの詳細を表示する
        holder.fbEdit.setOnClickListener {
            listener.onItemClickListener(position)
        }

    }

    private fun openFabMenu(holder: ViewHolder) {
        holder.isFabOpen = true
        holder.fbBlend.animate().rotation(60F)
        holder.fbDelete.animate()
            .translationX(-context.resources.getDimension(R.dimen.my_blend_fb)).rotation(0F)
        holder.fbShare.animate()
            .translationX(-context.resources.getDimension(R.dimen.my_blend_fb) * 2).rotation(0F)
        holder.fbEdit.animate()
            .translationX(-context.resources.getDimension(R.dimen.my_blend_fb) * 3).rotation(0F)
    }

    private fun closeFabMenu(holder: ViewHolder) {
        holder.isFabOpen = false
        holder.fbBlend.animate().rotation(0F)
        holder.fbDelete.animate()
            .translationX(0F).rotation(-180F)
        holder.fbShare.animate()
            .translationX(0F).rotation(-180F)
        holder.fbEdit.animate()
            .translationX(0F).rotation(-180F)
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