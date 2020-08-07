package com.monoprogram.myblend.presentation.top.DefaultRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDefaultRecipeBinding.bind(view)
        binding.default1.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, DefaultRecipeDetailFragment())?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DefaultRecipeViewModel::class.java)

    }

}