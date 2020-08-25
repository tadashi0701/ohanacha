package com.monoprogram.myblend.presentation.top.blend.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentAmountConfirmBinding
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel

class AmountConfirmFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private var herbList: ArrayList<String> = arrayListOf()
    private var valueList: ArrayList<Int> = arrayListOf()
    private lateinit var binding: FragmentAmountConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            herbList = it.getStringArrayList(HERB_LIST) as ArrayList<String>
            valueList = it.getIntegerArrayList(VALUE_LIST) as ArrayList<Int>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_amount_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAmountConfirmBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        private const val HERB_LIST = "herb_list"
        private const val VALUE_LIST = "value_list"

        @JvmStatic
        fun newInstance(param1: ArrayList<String>, param2: ArrayList<Int>) =
            AmountConfirmFragment()
                .apply {
                    arguments = Bundle().apply {
                        putStringArrayList(HERB_LIST, param1)
                        putIntegerArrayList(VALUE_LIST, param2)
                    }
                }
    }
}