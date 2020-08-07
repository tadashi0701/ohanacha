package com.monoprogram.myblend.presentation.top.DefaultRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeDetailBinding

class DefaultRecipeDetailFragment : Fragment() {

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
    }
}