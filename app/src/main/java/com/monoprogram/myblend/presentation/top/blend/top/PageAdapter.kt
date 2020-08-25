package com.monoprogram.myblend.presentation.top.blend.top

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
import com.monoprogram.myblend.presentation.top.blend.herblist.HerbListFragment

@Suppress("DEPRECATION")
class PageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            // どのFragmentを表示するか
            0 -> {
                return DefaultRecipeFragment()
            }
            1 -> {
                return HerbListFragment()
            }
            2 -> {
                return MyRecipeFragment()
            }
            else -> {
                return MyRecipeFragment()
            }
        }
    }

    // スワイプビューの数が2つだから
    override fun getCount(): Int {
        return 3;
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
            2 -> {
                return Application.instance.getString(R.string.previous_recipe)
            }
            else -> {
                return null
            }
        }
    }
}