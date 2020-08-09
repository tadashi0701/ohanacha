package com.monoprogram.myblend.presentation.top.DefaultRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeDetailBinding
import java.lang.String
import java.util.*

class DefaultRecipeDetailFragment : Fragment() {

    private val dataSet = arrayOfNulls<kotlin.String>(20)

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
        recyclerView.layoutManager = LinearLayoutManager(activity);

        var i = 0
        while (i < 20) {
            dataSet[i] = String.format(Locale.ENGLISH, "Data_0%d", i)
            i++
        }

        recyclerView.adapter = RecipeAdapter(dataSet)
    }
}