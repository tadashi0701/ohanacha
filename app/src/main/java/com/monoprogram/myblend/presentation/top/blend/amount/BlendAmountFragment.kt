package com.monoprogram.myblend.presentation.top.blend.amount

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.setPadding
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
    private var myAlertDialog: AlertDialog? = null

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
            if (!checkPercent()) return@setOnClickListener
            // ブレンド名の付与ダイアログ
            AlertDialog.Builder(activity).let {
                val msg = TextView(activity)
                val edit = EditText(activity)
                msg.setText(R.string.msg_blend_save)
                msg.textSize = 16F
                msg.setPadding(20)
                it.setCustomTitle(msg)
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

        // プログレスバー
        var sum = 0
        valueList.forEach { sum += it }
        binding.ProgressBarHorizontal.setProgress(sum, true)
        binding.textPercent.text = (sum * 10).toString() + "%"

        // 分量確認ボタン
        binding.btnAmount.setOnClickListener {
            if (!checkPercent()) return@setOnClickListener
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, AmountConfirmFragment.newInstance(herbList, valueList))
                ?.commit()
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

                // プログレスバーの値を更新
                var sum = 0
                valueList.forEach { sum += it }

                // 10を超えたら警告をして値を戻す
                if (sum > 10) {
                    if (myAlertDialog == null) {
                        myAlertDialog = AlertDialog.Builder(activity).let {
                            it.setMessage(R.string.msg_cannot_amount)
                            it.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                                valueList[position] = 0
                                myAlertDialog = null
                                adapter.updateValue(position, valueList)
                            })
                            it.setCancelable(false)
                            it.show()
                        }
                    }
                } else {
                    binding.ProgressBarHorizontal.setProgress(sum, true)
                    binding.textPercent.text = (sum * 10).toString() + "%"
                    adapter.updateValue(position, valueList)
                }
            }
        })
    }

    private fun checkPercent(): Boolean {
        var sum = 0
        valueList.forEach { sum += it }

        // 100%ではない為警告表示
        if (sum != 10) {
            AlertDialog.Builder(activity).let {
                it.setMessage(R.string.msg_100_percent)
                it.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                    //nop
                })
                it.setCancelable(false)
                it.show()
            }
            return false
        }
        return true
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