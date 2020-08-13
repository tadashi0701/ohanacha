package com.monoprogram.myblend.presentation.top.defaultrecipe

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
import com.monoprogram.myblend.databinding.FragmentDefaultRecipeDetailBinding
import com.monoprogram.myblend.entity.Herb


class DefaultRecipeDetailFragment : Fragment() {

    private lateinit var viewModel: DefaultRecipeViewModel

    private val values = arrayListOf(
        1, 2, 3, 4, 5, 6, 7, 8
    )

    // TODO: Rename and change types of parameters
    private var herbList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            herbList = it.getStringArrayList(HERB_LIST) as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDefaultRecipeDetailBinding.bind(view)
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DefaultRecipeViewModel::class.java)

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            createAdapter(it)
        })

        viewModel.onSelectedHerbList(herbList)
    }

    private fun createAdapter(herbInfo: List<Herb>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.my_recycler_view) ?: return
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = DefaultRecipeAdapter(herbInfo, values)
    }

    companion object {
        const val HERB_LIST = "herb_list"

        @JvmStatic
        fun newInstance(param1: ArrayList<String>) =
            DefaultRecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(HERB_LIST, param1)
                }
            }
    }

}