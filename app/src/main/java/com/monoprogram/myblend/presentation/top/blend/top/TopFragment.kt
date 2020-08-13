package com.monoprogram.myblend.presentation.top.blend.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import kotlinx.android.synthetic.main.fragment_top.*

class TopFragment : Fragment() {

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var adapter: PageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentManager = activity?.supportFragmentManager ?: return
        adapter = PageAdapter(fragmentManager)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        viewModel.blendInfo.observe(viewLifecycleOwner, Observer {
            adapter.notifyDataSetChanged()
        })
    }

}