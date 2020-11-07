package ao.inokri.devfestangola.ui.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.SplashViewModel
import ao.inokri.devfestangola.data.viewmodelsfactory.ViewModelFactory
import ao.inokri.devfestangola.ui.pages.main.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(
            this, ViewModelFactory(Repository())
        )[SplashViewModel::class.java]
    }

    override fun onStart() {
        timeSplash()
        super.onStart()
    }

    private fun timeSplash() {
        GlobalScope.launch {
            delay(3500).run {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}