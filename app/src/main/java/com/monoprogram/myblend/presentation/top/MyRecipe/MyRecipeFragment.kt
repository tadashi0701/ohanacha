package com.monoprogram.myblend.presentation.top.MyRecipe

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monoprogram.myblend.R

class MyRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = MyRecipeFragment()
    }

    private lateinit var viewModel: MyRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_recipe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}