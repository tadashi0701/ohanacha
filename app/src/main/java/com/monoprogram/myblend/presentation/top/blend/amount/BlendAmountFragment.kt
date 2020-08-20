package com.monoprogram.myblend.presentation.top.blend.amount

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentBlendAmountBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.top.TopFragment


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

            // ブレンド名の付与ダイアログ
            AlertDialog.Builder(activity).let {
                val edit = EditText(activity)
                it.setMessage(R.string.msg_blend_save)
                it.setView(edit)
                it.setNegativeButton("CANCEL", null)
                it.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                    viewModel.onClickedSave(herbList, valueList, edit.text.toString())
                    // ブレンド量調整の画面を削除する
                    activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                    // トップ画面を更新する
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container, TopFragment())?.commit()
                })
                it.show()
            }
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