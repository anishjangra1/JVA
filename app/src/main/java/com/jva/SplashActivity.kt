package com.jva
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jva.ui.login.LoginActivity
import com.jva.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    @Inject
    lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        setContentView(R.layout.activity_grid)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
//            if (appPreferences.getUserName()?.length!! > 1) {
//                val intent = Intent(this@SplashScreen, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
//            }

        }, 1500) // 3000 is the delayed time in milliseconds.
    }
}
