package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import com.google.gson.annotations.SerializedName

data class VehicleInfoResponseModel(
    @SerializedName("poiList")
    val vehicleList: List<VehicleInfoModel>
)

data class VehicleInfoModel(
    val id: Long? = null,
    val coordinate: CoordinateModel? = null,
    val fleetType: String? = null,
    val heading: Double? = null
)

data class CoordinateModel(
    val latitude: Double? = null,
    val longitude: Double? = null
)