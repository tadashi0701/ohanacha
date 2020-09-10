package com.monoprogram.myblend.presentations.top.HerbList

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.monoprogram.myblend.TopRouter
import com.monoprogram.myblend.databinding.FragmentHerbListBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentations.MyRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HerbListFragment : Fragment() {

    @Inject
    lateinit var router: TopRouter

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

            if (selectedList.isEmpty()) {
                AlertDialog.Builder(activity).let {
                    it.setMessage(R.string.msg_select_herb)
                    it.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                        //nop
                    })
                    it.setCancelable(false)
                    it.show()
                }
                return@setOnClickListener
            }

            val valueList: ArrayList<Int> = arrayListOf()
            selectedList.forEach { _ ->
                valueList.add(0)
            }
            router.showBlendAmountFragment(selectedList, valueList)
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

            override fun onHerbImageClickListener(position: Int) {
                router.showHerbDetailFragment(herbInfo[position].herbName)
            }
        })
    }
}