package com.bkarakoca.vehicletrackingapp.internal.injection.module

import com.bkarakoca.vehicletrackingapp.data.remote.api.VehicleService
import com.bkarakoca.vehicletrackingapp.data.remote.datasource.VehicleRemoteDataSource
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoMapper
import com.bkarakoca.vehicletrackingapp.data.repository.VehicleRepository
import com.bkarakoca.vehicletrackingapp.data.repository.VehicleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class VehicleModule {

    @Provides
    @Singleton
    fun provideVehicleService(retrofit: Retrofit): VehicleService =
        retrofit.create(VehicleService::class.java)

    @Provides
    @Singleton
    fun provideVehicleRepository(
        vehicleRemoteDataSource: VehicleRemoteDataSource,
        vehicleInfoMapper: VehicleInfoMapper
    ): VehicleRepository =
        VehicleRepositoryImpl(vehicleRemoteDataSource, vehicleInfoMapper)

}