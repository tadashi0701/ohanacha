package com.monoprogram.myblend.presentation.top.myrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentMyRecipeBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentMyRecipeBinding.bind(view)
        binding.btnCreateRecipe.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, MyRecipeBaseFragment())?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}