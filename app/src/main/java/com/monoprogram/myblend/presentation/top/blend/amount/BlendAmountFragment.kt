package com.monoprogram.myblend.presentation.top.blend.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentBlendAmountBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.top.MyRecipeFragment


class BlendAmountFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var binding: FragmentBlendAmountBinding

    private var herbList: ArrayList<String> = arrayListOf()
    private var valueList: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            herbList = it.getStringArrayList(HERB_LIST) as ArrayList<String>
            valueList = it.getIntegerArrayList(VALUE_LIST) as ArrayList<Int>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blend_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBlendAmountBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        viewModel.onSelectedHerbList(herbList)

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            createAdapter(it)
        })

        // 戻るボタン
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        // ブレンド保存ボタン
        binding.btnComp.setOnClickListener {
            viewModel.onClickedSave(herbList, valueList)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    private fun createAdapter(herbInfo: List<Herb>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.my_recycler_view) ?: return
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter =
            BlendAmountAdapter(
                herbInfo,
                valueList
            )
        recyclerView.adapter = adapter

        adapter.setOnSeekBarChangeListener(object : BlendAmountAdapter.OnSeekBarChangeListener {
            override fun OnSeekBarChangeListener(position: Int, value: Int) {
                valueList[position] = value
            }
        })
    }

    companion object {
        const val HERB_LIST = "herb_list"
        const val VALUE_LIST = "value_list"

        @JvmStatic
        fun newInstance(param1: ArrayList<String>, param2: ArrayList<Int>) =
            BlendAmountFragment()
                .apply {
                    arguments = Bundle().apply {
                        putStringArrayList(HERB_LIST, param1)
                        putIntegerArrayList(VALUE_LIST, param2)
                    }
                }
    }

}