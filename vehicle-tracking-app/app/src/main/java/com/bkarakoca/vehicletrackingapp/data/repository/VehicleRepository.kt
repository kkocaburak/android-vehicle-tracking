package com.bkarakoca.vehicletrackingapp.data.repository

import com.bkarakoca.vehicletrackingapp.data.remote.datasource.VehicleRemoteDataSource
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.LocationModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoMapper
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface VehicleRepository {
    suspend fun fetchVehicleList(
        firstLocation: LocationModel,
        secondLocation: LocationModel
    ): Flow<List<VehicleInfoUIModel>>
}

class VehicleRepositoryImpl @Inject constructor(
    private val remoteDataSource: VehicleRemoteDataSource,
    private val vehicleInfoMapper: VehicleInfoMapper
) : VehicleRepository {

    override suspend fun fetchVehicleList(
        firstLocation: LocationModel,
        secondLocation: LocationModel
    ): Flow<List<VehicleInfoUIModel>> = flow {
        remoteDataSource.fetchVehicleList(firstLocation, secondLocation).collect { response ->
            emit(vehicleInfoMapper.toUIModel(response))
        }
    }

}