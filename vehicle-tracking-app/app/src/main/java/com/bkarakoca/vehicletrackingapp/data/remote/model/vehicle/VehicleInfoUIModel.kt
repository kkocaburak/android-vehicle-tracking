package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.bkarakoca.vehicletrackingapp.base.ListAdapterItem
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleInfoUIModel(
    override val id: String? = null,
    val location: LatLng? = null,
    val fleetType: String? = null,
    val heading: Double? = null,
    @DrawableRes val drawableRes: Int
) : Parcelable, ListAdapterItem

fun VehicleInfoUIModel.getMapLocation(): LatLng {
    return location ?: LatLng(0.0, 0.0)
}