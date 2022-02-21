package com.bkarakoca.vehicletrackingapp.data.remote.api

import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleService {

    @GET(VEHICLES_INFORMATION)
    suspend fun fetchVehicleList(
        @Query(FIRST_LATITUDE) firstLatitude: String,
        @Query(FIRST_LONGITUDE) firstLongitude: String,
        @Query(SECOND_LATITUDE) secondLatitude: String,
        @Query(SECOND_LONGITUDE) secondLongitude: String
    ): VehicleInfoResponseModel

    companion object {
        const val FIRST_LATITUDE = "p1Lat"
        const val FIRST_LONGITUDE = "p1Lon"
        const val SECOND_LATITUDE = "p2Lat"
        const val SECOND_LONGITUDE = "p2Lon"

        const val VEHICLES_INFORMATION = "?"
    }

}