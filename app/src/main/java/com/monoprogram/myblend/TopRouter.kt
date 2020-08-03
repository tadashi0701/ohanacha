package com.monoprogram.myblend

import androidx.appcompat.app.AppCompatActivity
import com.monoprogram.myblend.top.TopFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import java.security.AccessControlContext
import javax.inject.Inject

@Component
interface TopRoute {
    fun showTopFragment()
}

@Module
class TopRouter @Inject constructor(
    private var context: AccessControlContext
) : TopRoute, AppCompatActivity() {
    @Provides
    override fun showTopFragment() {
        supportFragmentManager.beginTransaction().add(TopFragment(), "").commit()
    }

}
