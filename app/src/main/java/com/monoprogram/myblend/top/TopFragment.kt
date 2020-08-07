package com.monoprogram.myblend.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
import com.monoprogram.myblend.TopRouter
import com.monoprogram.myblend.create.CreateBlendFragment
import com.monoprogram.myblend.databinding.FragmentTopBinding
import javax.inject.Inject

class TopFragment : Fragment() {

    @Inject
    lateinit var router: TopRouter

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false).also { view ->
            Application.component.inject(this)
            val binding = FragmentTopBinding.bind(view)

            // 新規ブレンド作成ボタンを押下
            binding.createBlendBtn.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container, CreateBlendFragment())?.commit()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}