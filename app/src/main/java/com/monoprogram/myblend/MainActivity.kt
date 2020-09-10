package com.monoprogram.myblend


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.monoprogram.myblend.presentations.MyRecipeViewModel
import com.monoprogram.myblend.presentations.amount.AmountConfirmFragment
import com.monoprogram.myblend.presentations.amount.BlendAmountFragment
import com.monoprogram.myblend.presentations.detail.HerbDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var router: TopRouter

    lateinit var mAdView: AdView

    private lateinit var viewModel: MyRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // viewmodelの定義
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)

        // 表示画面の定義
        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        // 広告の初期化
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //　router.showTopFragment() -> 期待通りにrouterが動作しない為、一旦コメントアウト
        // トップ画面のフラグ面をセット
        router.showTopFragment()

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) ?: return
        when (fragment) {
            is AmountConfirmFragment,
            is BlendAmountFragment,
            is HerbDetailFragment -> {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}