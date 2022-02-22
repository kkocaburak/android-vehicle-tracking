package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import com.bkarakoca.vehicletrackingapp.R
import javax.inject.Inject

class VehicleInfoMapper @Inject constructor() {

    fun toUIModel(responseModel: VehicleInfoResponseModel): List<VehicleInfoUIModel> {
        return responseModel.vehicleList.sortedByDescending { it.id }.map {
            with (it) {
                VehicleInfoUIModel(
                    id = id.toString(),
                    location = getVehicleLocation(),
                    fleetType = fleetType,
                    heading = heading,
                    drawableRes = getDrawableByFleetType(fleetType)
                )
            }
        }
    }

    private fun getDrawableByFleetType(fleetType: String?) : Int {
        return when (fleetType) {
            "TAXI" -> R.drawable.ic_taxi
            "POOLING" -> R.drawable.ic_pooling
            else -> R.drawable.ic_default_vehicle
        }
    }

}