package com.monoprogram.myblend

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.monoprogram.myblend.presentations.amount.AmountConfirmFragment
import com.monoprogram.myblend.presentations.amount.BlendAmountFragment
import com.monoprogram.myblend.presentations.top.TopFragment
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

    fun showBlendAmountFragment(herb: ArrayList<String>, value: ArrayList<Int>) {
        context.supportFragmentManager.beginTransaction().add(
            R.id.container, BlendAmountFragment.newInstance(
                herb,
                value
            )
        ).commit()
    }

    fun removeBlendAmountFragment() {
        // ブレンド量調整の画面を削除する
        context.supportFragmentManager.beginTransaction().remove(BlendAmountFragment()).commit()
        // トップ画面を更新する
        context.supportFragmentManager.beginTransaction()
            .replace(R.id.container, TopFragment()).commit()
    }

    fun showAmountConfirmFragment(herbList: ArrayList<String>, valueList: ArrayList<Int>) {
        context.supportFragmentManager.beginTransaction()
            .add(R.id.container, AmountConfirmFragment.newInstance(herbList, valueList))
            .commit()
    }
}
