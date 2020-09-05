package com.monoprogram.myblend

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.monoprogram.myblend.presentation.top.blend.top.MyBlend.MyRecipeFragment
import com.monoprogram.myblend.presentation.top.blend.top.TopFragment
import javax.inject.Inject

class TopRouter @Inject constructor(context: Context) {

    private val context = context as FragmentActivity

    fun showTopFragment() {
        context.supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                TopFragment()
            )
            .commit()
    }

    fun showCreateBledFragment() {
        context.supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                MyRecipeFragment()
            )
            .commit()
    }
}
