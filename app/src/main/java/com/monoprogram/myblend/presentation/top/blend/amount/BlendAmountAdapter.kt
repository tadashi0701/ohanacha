package com.monoprogram.myblend.presentation.top.blend.amount

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.entity.Herb


class BlendAmountAdapter internal constructor(
    private var herbInfo: List<Herb>,
    private var itemValues: ArrayList<Int>
) :
    RecyclerView.Adapter<BlendAmountAdapter.ViewHolder>() {

    lateinit var listener: OnSeekBarChangeListener
    private var amountList: ArrayList<Herb> = arrayListOf()

    init {
        amountList = herbInfo as ArrayList<Herb>
    }

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
        return ViewHolder(
            view
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(herbInfo[position].imageId)
        holder.textView.text = herbInfo[position].herbName
        holder.seekBar.progress = itemValues[position]

        holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listener.OnSeekBarChangeListener(holder.adapterPosition, p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                // nop
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // nop
            }

        })
    }

    fun updateValue(position: Int, valueList: ArrayList<Int>) {
        itemValues = valueList
        notifyItemChanged(position)
    }

    //インターフェースの作成
    interface OnSeekBarChangeListener {
        fun OnSeekBarChangeListener(position: Int, value: Int)
    }

    fun setOnSeekBarChangeListener(listener: OnSeekBarChangeListener) {
        this.listener = listener
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return herbInfo.size
    }

}