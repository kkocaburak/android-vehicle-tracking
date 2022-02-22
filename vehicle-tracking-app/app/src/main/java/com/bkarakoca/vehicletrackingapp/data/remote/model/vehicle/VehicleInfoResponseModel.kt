package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class VehicleInfoResponseModel(
    @SerializedName("poiList")
    val vehicleList: List<VehicleInfoModel>
)

data class VehicleInfoModel(
    val id: Long? = null,
    val coordinate: LocationModel? = null,
    val fleetType: String? = null,
    val heading: Double? = null
)

data class LocationModel(
    val latitude: Double? = null,
    val longitude: Double? = null
)

fun VehicleInfoModel.getVehicleLocation(): LatLng {
    val latitude = coordinate?.latitude ?: 0.0
    val longitude = coordinate?.longitude ?: 0.0
    return LatLng(latitude, longitude)
}