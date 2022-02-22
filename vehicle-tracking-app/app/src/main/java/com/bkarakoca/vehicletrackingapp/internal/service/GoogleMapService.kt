package com.bkarakoca.vehicletrackingapp.internal.service

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap

class GoogleMapService : MapService {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    @SuppressLint("PotentialBehaviorOverride")
    override suspend fun onCreate(
        activity: FragmentActivity,
        containerResourceId: Int,
        mapInitializedListener: MapInitializedListener,
        itemClickListener: MapItemClickListener
    ) {
        onCreate(activity, containerResourceId)
        googleMap = mapFragment.awaitMap()
        googleMap.setOnMarkerClickListener {
            itemClickListener.onClick(it)
        }
        mapInitializedListener.onMapInitialized()
    }

    override fun onCreate(activity: FragmentActivity, containerResourceId: Int) {
        mapFragment = SupportMapFragment.newInstance()
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerResourceId, mapFragment)
        fragmentTransaction.commit()
    }

    override fun addMarkers(markerList: List<MarkerOptions>) {
        markerList.forEach { markerOption ->
            googleMap.addMarker(markerOption)
        }
    }

    override fun moveCameraToLocation(location: LatLng?, zoomValue: Float) {
        location?.let {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, zoomValue))
        }
    }

    override fun animateCamera(location: LatLng?) {
        animateCamera(location, null)
    }

    override fun animateCamera(location: LatLng?, zoomValue: Float?) {
        location?.let { safeLocation ->
            zoomValue?.let { safeZoomValue ->
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(safeLocation, safeZoomValue))
            } ?: googleMap.animateCamera(CameraUpdateFactory.newLatLng(safeLocation))
        }
    }

    override fun setMaxZoomPreference(zoomValue: Float) {
        googleMap.setMaxZoomPreference(zoomValue)
    }

    override fun destroyMap() {
        googleMap.clear()
        mapFragment.onDestroyView()
    }
}