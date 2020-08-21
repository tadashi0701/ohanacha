package com.monoprogram.myblend


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.monoprogram.myblend.presentation.top.blend.MyRecipeViewModel
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment
import com.monoprogram.myblend.presentation.top.blend.top.TopFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var router: TopRouter

    lateinit var mAdView: AdView

    private lateinit var viewModel: MyRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MyRecipeViewModel::class.java)
        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // 広告の初期化
        Application.component.inject(this)
        MobileAds.initialize(this) {}
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //　router.showTopFragment() -> 期待通りにrouterが動作しない為、一旦コメントアウト
        // トップ画面のフラグ面をセット
        supportFragmentManager.beginTransaction().add(
            R.id.container,
            TopFragment()
        ).commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) ?: return
        when {
            fragment is BlendAmountFragment -> {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}