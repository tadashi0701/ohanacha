package com.monoprogram.myblend

import androidx.fragment.app.Fragment
import com.monoprogram.myblend.presentation.top.MyRecipe.MyRecipeFragment
import com.monoprogram.myblend.presentation.top.TopFragment
import javax.inject.Inject

interface TopRoute {
    fun showTopFragment()
    fun showCreateBledFragment()
}

class TopRouter @Inject constructor(
) : TopRoute, Fragment() {

    override fun showTopFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, TopFragment())
            ?.commit()
    }

    override fun showCreateBledFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, MyRecipeFragment())
            ?.commit()
    }
}
