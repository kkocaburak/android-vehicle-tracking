package com.bkarakoca.vehicletrackingapp.internal.service

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

interface MapService {
    suspend fun onCreate(
        activity: FragmentActivity,
        @IdRes containerResourceId: Int,
        mapInitializedListener: MapInitializedListener,
        itemClickListener: MapItemClickListener
    )

    fun onCreate(
        activity: FragmentActivity,
        @IdRes containerResourceId: Int
    )

    fun addMarkers(markerList: List<MarkerOptions>)

    fun moveCameraToLocation(location: LatLng?, zoomValue: Float)

    fun animateCamera(location: LatLng?)

    fun animateCamera(location: LatLng?, zoomValue: Float?)

    fun setMaxZoomPreference(zoomValue: Float)

    fun destroyMap()
}

interface MapItemClickListener {
    fun onClick(marker: Marker): Boolean
}

interface MapInitializedListener {
    fun onMapInitialized()
}
