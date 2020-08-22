package com.monoprogram.myblend.presentation.top.blend.myblend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentHerbListBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment

class HerbListFragment : Fragment() {

    private lateinit var binding: FragmentHerbListBinding
    private lateinit var viewModel: MyRecipeViewModel
    private var selectedList: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_herb_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHerbListBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        // ハーブ情報を生成する(名前とか説明)
        viewModel.onCreateHerbInfo()

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            // ハーブ情報をもとにRecyclerViewを生成する
            createAdapter(it)
        })

        // 進むボタン検知
        binding.btnNext.setOnClickListener {
            val valueList: ArrayList<Int> = arrayListOf()
            selectedList.forEach { _ ->
                valueList.add(0)
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(
                    R.id.container,
                    BlendAmountFragment.newInstance(
                        selectedList,
                        valueList
                    )
                )?.commit()
        }
    }

    private fun createAdapter(herbInfo: List<Herb>) {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.base_recycler_view) ?: return
        val adapter =
            HerbListAdapter(
                herbInfo
            )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : HerbListAdapter.OnItemClickListener {
            override fun onItemClickListener(list: ArrayList<String>) {
                selectedList = list
            }
        })
    }
}