package com.monoprogram.myblend.presentation.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentTopBinding
import kotlinx.android.synthetic.main.fragment_top.*

class TopFragment : Fragment() {

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentManager = activity?.supportFragmentManager ?: return
        pager.adapter = PageAdapter(fragmentManager)
        tabLayout.setupWithViewPager(pager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}