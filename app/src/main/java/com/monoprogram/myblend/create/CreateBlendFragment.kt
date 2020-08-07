package com.monoprogram.myblend.create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monoprogram.myblend.R

class CreateBlendFragment : Fragment() {

    companion object {
        fun newInstance() = CreateBlendFragment()
    }

    private lateinit var viewModel: CreateBlendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_blend_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateBlendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}