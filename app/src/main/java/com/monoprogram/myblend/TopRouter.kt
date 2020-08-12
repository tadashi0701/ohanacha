package com.monoprogram.myblend

import androidx.appcompat.app.AppCompatActivity
import com.monoprogram.myblend.presentation.top.myrecipe.MyRecipeFragment
import com.monoprogram.myblend.presentation.top.TopFragment
import javax.inject.Inject

interface TopRoute {
    fun showTopFragment()
    fun showCreateBledFragment()
}

class TopRouter @Inject constructor(
) : TopRoute, AppCompatActivity() {

    override fun showTopFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TopFragment())
            .commit()
    }

    override fun showCreateBledFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MyRecipeFragment())
            .commit()
    }
}
