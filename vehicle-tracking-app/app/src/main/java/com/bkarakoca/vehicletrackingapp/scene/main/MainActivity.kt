package com.bkarakoca.vehicletrackingapp.scene.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseBindingActivity
import com.bkarakoca.vehicletrackingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        binder.viewModel = viewModel
        binder.lifecycleOwner = this
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
