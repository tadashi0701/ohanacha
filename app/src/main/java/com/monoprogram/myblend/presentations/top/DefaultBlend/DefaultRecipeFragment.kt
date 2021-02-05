package com.monoprogram.myblend.presentations.top.DefaultBlend

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
import com.monoprogram.myblend.TopRouter
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeBinding
import com.monoprogram.myblend.entity.Blend
import com.monoprogram.myblend.presentations.MyRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DefaultRecipeFragment : Fragment() {

    @Inject
    lateinit var router: TopRouter

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

        viewModel.setDefaultRecipe()

        viewModel.defaultBlend.observe(viewLifecycleOwner, Observer {
            createAdapter(it)
        })
    }

    private fun createAdapter(blendInfo: List<Blend>) {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.default_recipe_recycler_view) ?: return
        val adapter =
            DefaultRecipeAdapter(
                blendInfo
            )
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : DefaultRecipeAdapter.OnItemClickListener {
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
                router.showBlendAmountFragment(herb, value)
            }
        })
    }

}