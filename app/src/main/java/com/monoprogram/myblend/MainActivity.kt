package com.monoprogram.myblend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monoprogram.myblend.top.TopFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, TopFragment()).commit()
    }
}