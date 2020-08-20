package com.monoprogram.myblend


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment
import com.monoprogram.myblend.presentation.top.blend.top.TopFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var router: TopRouter
    lateinit var mAdView: AdView


    private val dao = Application.database.herbDao()

    private val names = arrayListOf(
        Application.instance.getString(R.string.RoseHip),
        Application.instance.getString(R.string.Hibiscus),
        Application.instance.getString(R.string.Lemongrass),
        Application.instance.getString(R.string.Heath),
        Application.instance.getString(R.string.Bardock),
        Application.instance.getString(R.string.Stevia),
        Application.instance.getString(R.string.LemonPeople),
        Application.instance.getString(R.string.LemonMyrtle),
        Application.instance.getString(R.string.Rooibos),
        Application.instance.getString(R.string.Chamomile),
        Application.instance.getString(R.string.Mint),
        Application.instance.getString(R.string.Raspberry),
        Application.instance.getString(R.string.LemonVerbena),
        Application.instance.getString(R.string.DandyLion)
    )
    private val photos = arrayListOf(
        R.drawable.rosehip, R.drawable.hibiscus, R.drawable.lemongrass,
        R.drawable.heath, R.drawable.bardock, R.drawable.stebia,
        R.drawable.lemonpeople, R.drawable.lemonmyrtle, R.drawable.rooibos,
        R.drawable.camomile, R.drawable.mint, R.drawable.raspberry, R.drawable.lemonverbena,
        R.drawable.dandylion
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Application.component.inject(this)
        MobileAds.initialize(this) {}
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //router.showTopFragment() -> 期待通りにrouterが動作しない為、一旦コメントアウト
        supportFragmentManager.beginTransaction().add(
            R.id.container,
            TopFragment()
        ).commit()

        // DB情報の初期化
        initHerbInfo()
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

    private fun initHerbInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                if (dao.getAll().isNotEmpty()) return@withContext
                names.forEachIndexed { index, name ->
                    dao.insert(Herb(0, name, "香りが良い", photos[index]))
                }
            }
        }
    }
}