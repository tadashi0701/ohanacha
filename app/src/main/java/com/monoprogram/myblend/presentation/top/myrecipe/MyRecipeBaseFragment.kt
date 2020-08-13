package com.monoprogram.myblend.presentation.top.myrecipe

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
import com.monoprogram.myblend.databinding.FragmentMyRecipeBaseBinding
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.defaultrecipe.DefaultRecipeDetailFragment

class MyRecipeBaseFragment : Fragment() {

    private lateinit var binding: FragmentMyRecipeBaseBinding
    private lateinit var viewModel: MyRecipeViewModel
    private var selectList: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_recipe_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMyRecipeBaseBinding.bind(view)
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            createAdapter(it)
        })

        viewModel.onUpdateHerbInfo()

        binding.btnComp.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(
                    R.id.container,
                    DefaultRecipeDetailFragment.newInstance(selectList)
                )?.commit()
        }
    }

    private fun createAdapter(herbInfo: List<Herb>) {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.base_recycler_view) ?: return
        val adapter = MyRecipeBaseAdapter(herbInfo)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : MyRecipeBaseAdapter.OnItemClickListener {
            override fun onItemClickListener(herbName: String, needsAdd: Boolean) {
                selectList.find { it == herbName }.also {
                    if (needsAdd) {
                        it ?: selectList.add(herbName)
                    } else {
                        it ?: return@also
                        selectList.remove(herbName)
                    }
                }

            }
        })
    }
}