package com.bkarakoca.vehicletrackingapp.data.remote.datasource

import com.bkarakoca.vehicletrackingapp.data.remote.BaseRemoteDataSource
import com.bkarakoca.vehicletrackingapp.data.remote.api.VehicleService
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.LocationModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRemoteDataSource @Inject constructor(
    private val service: VehicleService
) : BaseRemoteDataSource() {

    suspend fun fetchVehicleList(
        firstLocation: LocationModel,
        secondLocation: LocationModel
    ): Flow<VehicleInfoResponseModel> = invokeFlow {
        service.fetchVehicleList(
            firstLocation.latitude.toString(),
            firstLocation.longitude.toString(),
            secondLocation.latitude.toString(),
            secondLocation.longitude.toString()
        )
    }

}