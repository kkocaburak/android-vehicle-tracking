package com.bkarakoca.vehicletrackingapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.data.remote.datasource.VehicleRemoteDataSource
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.LocationModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoMapper
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoResponseModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VehicleRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var remoteDataSource: VehicleRemoteDataSource

    private var mapper = VehicleInfoMapper()

    private lateinit var vehicleRepository: VehicleRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        vehicleRepository = VehicleRepositoryImpl(remoteDataSource, mapper)
    }

    @Test
    fun `when repository answers success should return ui model with descending order`(): Unit = runBlocking {
        // given
        coEvery { remoteDataSource.fetchVehicleList(any(), any()) } coAnswers { flow { emit(mockResponse) }}

        // when
        vehicleRepository.fetchVehicleList(LocationModel(), LocationModel()).collect { uiModel ->
            // taxi
            val taxiItem = uiModel[2]
            Assert.assertTrue(taxiItem.id == "1")
            Assert.assertTrue(taxiItem.fleetType == "TAXI")
            Assert.assertTrue(taxiItem.drawableRes == R.drawable.ic_taxi)

            // pooling
            val poolingItem = uiModel[1]
            Assert.assertTrue(poolingItem.id == "2")
            Assert.assertTrue(poolingItem.fleetType == "POOLING")
            Assert.assertTrue(poolingItem.drawableRes == R.drawable.ic_pooling)

            // custom
            val customItem = uiModel[0]

            Assert.assertTrue(customItem.id == "3")
            Assert.assertTrue(customItem.fleetType != "TAXI")
            Assert.assertTrue(customItem.fleetType != "POOLING")
            Assert.assertTrue(customItem.drawableRes == R.drawable.ic_default_vehicle)
        }
    }

    private val mockResponse = VehicleInfoResponseModel(
        listOf(
            VehicleInfoModel(
                id = 1,
                coordinate = LocationModel(1.0, 1.0),
                fleetType = "TAXI",
                heading = 1.0
            ),
            VehicleInfoModel(
                id = 2,
                coordinate = LocationModel(2.0, 2.0),
                fleetType = "POOLING",
                heading = 1.0
            ),
            VehicleInfoModel(
                id = 3,
                coordinate = LocationModel(3.0, 3.0),
                fleetType = "UNIQUE",
                heading = 1.0
            ),

        )
    )

}