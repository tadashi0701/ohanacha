package com.monoprogram.myblend.presentation.top.blend.amount

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentAmountConfirmBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel

class AmountConfirmFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var binding: FragmentAmountConfirmBinding
    private lateinit var adapter: AmountConfirmAdapter
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

        viewModel.onSelectedHerbList(herbList)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            createAdapter(it as ArrayList<Herb>)
        })

        binding.editCups.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var input: Int
                p0.toString().let {
                    input = if (it.isEmpty())
                        0
                    else
                        it.toInt()
                }
                adapter.updateValue(calcCups(valueList, input))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nop
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nop
            }

        })
    }

    private fun createAdapter(herbList: ArrayList<Herb>) {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.amount_recycler_view) ?: return
        adapter =
            AmountConfirmAdapter(
                herbList,
                calcCups(valueList, 10)
            )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun calcCups(valList: ArrayList<Int>, calc: Int): ArrayList<Float> {
        val retList: ArrayList<Float> = arrayListOf()
        valList.forEach {
            retList.add((it / 10F) * 3F * calc)
        }
        return retList
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