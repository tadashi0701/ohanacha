package com.monoprogram.myblend


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.presentation.top.blend.top.TopFragment
import com.monoprogram.myblend.presentation.top.blend.amount.BlendAmountFragment
import com.monoprogram.myblend.presentation.top.blend.myblend.MyBlendFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var router: TopRouter

    private val dao = Application.database.herbDao()

    private val names = arrayListOf(
        "chamomile", "jasmine", "lavendar",
        "lemongrass", "mint", "rosemary",
        "sage", "thyme"
    )
    private val photos = arrayListOf(
        R.drawable.chamomile, R.drawable.jasmine, R.drawable.lavendar,
        R.drawable.lemongrass, R.drawable.mint, R.drawable.rosemary,
        R.drawable.sage, R.drawable.thyme
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Application.component.inject(this)
        //router.showTopFragment() -> 期待通りにrouterが動作しない為、一旦コメントアウト
        supportFragmentManager.beginTransaction().add(R.id.container,
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
            fragment is MyBlendFragment -> {
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