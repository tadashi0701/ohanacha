package com.monoprogram.myblend.presentation.top.blend.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeBinding
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment

class DefaultRecipeFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var binding: FragmentDefaultRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDefaultRecipeBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        binding.default1.setOnClickListener {
            viewModel.onClickedDefault(1)
        }
        binding.default2.setOnClickListener {
            viewModel.onClickedDefault(2)
        }
        binding.default3.setOnClickListener {
            viewModel.onClickedDefault(3)
        }
        binding.default4.setOnClickListener {
            viewModel.onClickedDefault(4)
        }

        viewModel.defaultRecipe.observe(viewLifecycleOwner, Observer {
            val herbList: ArrayList<String> = arrayListOf()
            val valueList: ArrayList<Int> = arrayListOf()
            it.forEach { blend ->
                herbList.add(blend.first)
                valueList.add(blend.second)
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(
                    R.id.container,
                    BlendAmountFragment.newInstance(herbList, valueList)
                )?.commit()
        })

    }

}