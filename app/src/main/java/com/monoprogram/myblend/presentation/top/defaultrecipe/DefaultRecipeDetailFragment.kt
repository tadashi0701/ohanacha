package com.monoprogram.myblend.presentation.top.defaultrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeDetailBinding


class DefaultRecipeDetailFragment : Fragment() {

    private val names = arrayListOf(
        "chamomile", "jasmine", "lavendar",
        "lemongrass", "mint", "rosemary",
        "sage", "thyme"
    )

    private val values = arrayListOf(
        1, 2, 3, 4, 5, 6, 7, 8
    )

    private val photos = arrayListOf(
        R.drawable.chamomile, R.drawable.jasmine, R.drawable.lavendar,
        R.drawable.lemongrass, R.drawable.mint, R.drawable.rosemary,
        R.drawable.sage, R.drawable.thyme
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDefaultRecipeDetailBinding.bind(view)
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = RecipeAdapter(photos, names, values)
    }
}