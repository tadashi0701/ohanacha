package com.monoprogram.myblend.presentation.top.DefaultRecipe

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monoprogram.myblend.R

class DefaultRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = DefaultRecipeFragment()
    }

    private lateinit var viewModel: DefaultRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_recipe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DefaultRecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}