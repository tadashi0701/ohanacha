package com.monoprogram.myblend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monoprogram.myblend.top.TopFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: TopRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Application.component.inject(this)
        //router.showTopFragment() -> 期待通りにrouterが動作しない為、一旦コメントアウト
        supportFragmentManager.beginTransaction().add(R.id.container, TopFragment()).commit()
    }
}