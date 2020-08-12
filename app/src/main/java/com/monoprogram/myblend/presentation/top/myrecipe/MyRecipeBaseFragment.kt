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

class MyRecipeBaseFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_recipe_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentMyRecipeBaseBinding.bind(view)
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        viewModel.herbInfo.observe(viewLifecycleOwner, Observer {
            val recyclerView =
                view?.findViewById<RecyclerView>(R.id.base_recycler_view) ?: return@Observer
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)

            recyclerView.adapter = MyRecipeBaseAdapter(it)
        })

        viewModel.onUpdateHerbInfo()
    }
}