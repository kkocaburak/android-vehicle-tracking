package com.bkarakoca.vehicletrackingapp.domain.vehicle

import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.LocationModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.data.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehicleListUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    data class Params(
        val firstLocation: LocationModel,
        val secondLocation: LocationModel
    )

    suspend fun fetchVehicleList(params: Params): Flow<List<VehicleInfoUIModel>> =
        vehicleRepository.fetchVehicleList(
            params.firstLocation,
            params.secondLocation
        )

}