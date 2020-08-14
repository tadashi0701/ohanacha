package com.monoprogram.myblend.presentation.top.blend.top

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
import com.monoprogram.myblend.databinding.FragmentMyRecipeBinding
import com.monoprogram.myblend.entity.Blend
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment
import com.monoprogram.myblend.presentation.top.blend.myblend.MyBlendFragment

class MyRecipeFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var binding: FragmentMyRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMyRecipeBinding.bind(view)
        binding.btnCreateRecipe.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(
                    R.id.container,
                    MyBlendFragment()
                )?.commit()
        }
        binding.fabCreateRecipe.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(
                    R.id.container,
                    MyBlendFragment()
                )?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        viewModel.onUpdateBlendInfo()

        viewModel.blendInfo.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.btnCreateRecipe.visibility = View.VISIBLE
                binding.textCreateRecipe.visibility = View.VISIBLE
                binding.fabCreateRecipe.visibility = View.INVISIBLE
            } else {
                binding.btnCreateRecipe.visibility = View.INVISIBLE
                binding.textCreateRecipe.visibility = View.INVISIBLE
                binding.fabCreateRecipe.visibility = View.VISIBLE
                createAdapter(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onUpdateBlendInfo()
    }

    private fun createAdapter(blendInfo: List<Blend>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.my_recipe_recycler_view) ?: return
        val adapter = MyRecipeAdapter(blendInfo)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : MyRecipeAdapter.OnItemClickListener {
            override fun onItemClickListener(position: Int) {
                // 選択されたブレンドを表示
                val herb: ArrayList<String> = arrayListOf()
                blendInfo[position].herbName.split(",").also { list ->
                    list.forEach { herb.add(it) }
                }
                val value: ArrayList<Int> = arrayListOf()
                blendInfo[position].herbValue.split(",").also { list ->
                    list.forEach { value.add(it.toInt()) }
                }

                activity?.supportFragmentManager?.beginTransaction()?.add(
                    R.id.container, BlendAmountFragment.newInstance(
                        herb,
                        value
                    )
                )?.commit()
            }
        })
    }

}