package com.monoprogram.myblend.presentation.top

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
import com.monoprogram.myblend.presentation.top.DefaultRecipe.DefaultRecipeFragment
import com.monoprogram.myblend.presentation.top.MyRecipe.MyRecipeFragment

@Suppress("DEPRECATION")
class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            // どのFragmentを表示するか
            0 -> {
                return DefaultRecipeFragment()
            }
            1 -> {
                return MyRecipeFragment()
            }
            else -> {
                return DefaultRecipeFragment()
            }
        }
    }

    // スワイプビューの数が2つだから
    override fun getCount(): Int {
        return 2;
    }

    // スワイプビューのタイトルを決める
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return Application.instance.getString(R.string.default_recipe)
            }
            1 -> {
                return Application.instance.getString(R.string.my_recipe)
            }
            else -> {
                return null
            }
        }
    }
}