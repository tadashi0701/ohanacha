package com.monoprogram.myblend.presentations.top.MyBlend

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.monoprogram.myblend.databinding.FragmentMyBlendBinding
import com.monoprogram.myblend.entity.Blend
import com.monoprogram.myblend.presentations.MyRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyRecipeFragment : Fragment() {

    @Inject
    lateinit var router: TopRouter

    private lateinit var viewModel: MyRecipeViewModel
    private lateinit var binding: FragmentMyBlendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_blend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMyBlendBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        viewModel.onUpdateBlendInfo()

        viewModel.blendInfo.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.textCreateRecipe.visibility = View.VISIBLE
            } else {
                binding.textCreateRecipe.visibility = View.INVISIBLE
            }
            createAdapter(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onUpdateBlendInfo()
    }

    private fun createAdapter(blendInfo: List<Blend>) {
        val c = context ?: return
        val recyclerView = view?.findViewById<RecyclerView>(R.id.my_recipe_recycler_view) ?: return
        val adapter =
            MyRecipeAdapter(
                c,
                blendInfo
            )
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
                router.showBlendAmountFragment(herb, value)
            }

            override fun onItemDeleteClickListener(position: Int) {
                // 選択されたブレンドを削除
                AlertDialog.Builder(activity).let {
                    it.setMessage(R.string.msg_delete_blend)
                    it.setNegativeButton("CANCEL", null)
                    it.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                        viewModel.deleteBlend(blendInfo[position])
                    })
                    it.setCancelable(false)
                    it.show()
                }
                blendInfo[position].id
            }
        })
    }
}