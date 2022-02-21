package com.bkarakoca.vehicletrackingapp.application

import android.app.Application
import com.bkarakoca.vehicletrackingapp.internal.util.NetworkStateHolder.registerConnectivityMonitor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        registerConnectivityMonitor()
    }
}