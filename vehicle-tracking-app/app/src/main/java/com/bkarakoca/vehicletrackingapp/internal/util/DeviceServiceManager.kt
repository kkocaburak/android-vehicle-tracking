package com.bkarakoca.vehicletrackingapp.internal.util

import com.bkarakoca.vehicletrackingapp.internal.service.GoogleMapService
import com.bkarakoca.vehicletrackingapp.internal.service.MapService

object DeviceServiceManager {
    fun provideMapService(): MapService {
        return GoogleMapService()
    }
}