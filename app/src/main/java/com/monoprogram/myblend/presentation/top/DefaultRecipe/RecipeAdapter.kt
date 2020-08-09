package com.monoprogram.myblend.presentation.top.DefaultRecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R


class RecipeAdapter internal constructor(myDataset: Array<String?>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    private var dataset = arrayOfNulls<String>(20)

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var mTextView: TextView

        init {
            mTextView = v.findViewById(R.id.text_view)
        }
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
        holder.mTextView.text = dataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    init {
        dataset = myDataset
    }
}