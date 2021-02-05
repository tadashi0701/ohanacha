package com.monoprogram.myblend.presentations.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monoprogram.myblend.R
import com.monoprogram.myblend.databinding.FragmentHerbDetailBinding
import com.monoprogram.myblend.presentations.MyRecipeViewModel

class HerbDetailFragment : Fragment() {

    private var herbName: String = ""
    private lateinit var binding: FragmentHerbDetailBinding
    private lateinit var viewModel: MyRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            herbName = it.getString(HERB_NAME) ?: return@let
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_herb_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHerbDetailBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        viewModel.onDetailHerbInfo(herbName)

        viewModel.detailHerbInfo.observe(viewLifecycleOwner, Observer {
            binding.imageHerbDetail.setImageResource(it.imageId)
            binding.textHerbDetail.text = it.description
        })

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        private const val HERB_NAME = "herb_name"
        fun newInstance(herbName: String) =
            HerbDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(HERB_NAME, herbName)
                }
            }
    }
}