package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import com.bkarakoca.vehicletrackingapp.R
import javax.inject.Inject

class VehicleInfoMapper @Inject constructor() {

    fun toUIModel(responseModel: VehicleInfoResponseModel): List<VehicleInfoUIModel> {
        return responseModel.vehicleList.map {
            VehicleInfoUIModel(
                id = it.id.toString(),
                latitude = it.coordinate?.latitude,
                longitude = it.coordinate?.longitude,
                fleetType = it.fleetType,
                heading = it.heading,
                imageRes = R.drawable.ic_launcher_foreground
            )
        }
    }

}