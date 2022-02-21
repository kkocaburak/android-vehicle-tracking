package com.bkarakoca.vehicletrackingapp.data.remote.datasource

import com.bkarakoca.vehicletrackingapp.data.remote.BaseRemoteDataSource
import com.bkarakoca.vehicletrackingapp.data.remote.api.VehicleService
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.CoordinateModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRemoteDataSource @Inject constructor(
    private val service: VehicleService
) : BaseRemoteDataSource() {

    suspend fun fetchVehicleList(
        firstCoordinate: CoordinateModel,
        secondCoordinate: CoordinateModel
    ): Flow<VehicleInfoResponseModel> = invokeFlow {
        service.fetchVehicleList(
            firstCoordinate.latitude.toString(),
            firstCoordinate.longitude.toString(),
            secondCoordinate.latitude.toString(),
            secondCoordinate.longitude.toString()
        )
    }

}