package com.bkarakoca.vehicletrackingapp.domain.vehicle

import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.CoordinateModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.data.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehicleListUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    data class Params(
        val firstCoordinate: CoordinateModel,
        val secondCoordinate: CoordinateModel
    )

    suspend fun fetchVehicleList(params: Params): Flow<List<VehicleInfoUIModel>> =
        vehicleRepository.fetchVehicleList(
            params.firstCoordinate,
            params.secondCoordinate
        )

}