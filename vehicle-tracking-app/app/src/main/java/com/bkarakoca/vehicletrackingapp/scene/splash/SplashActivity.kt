package com.bkarakoca.vehicletrackingapp.scene.splash

import android.os.Bundle
import com.bkarakoca.vehicletrackingapp.base.BaseActivity
import com.bkarakoca.vehicletrackingapp.scene.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startMainActivity()
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
