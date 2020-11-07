package ao.inokri.devfestangola.data.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import ao.inokri.devfestangola.ui.pages.main.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun Context.timeSplash(callback: () -> Unit) {
        GlobalScope.launch {
            delay(5)
            startActivity(Intent(this@timeSplash, MainActivity::class.java))
            callback()
        }
    }
}