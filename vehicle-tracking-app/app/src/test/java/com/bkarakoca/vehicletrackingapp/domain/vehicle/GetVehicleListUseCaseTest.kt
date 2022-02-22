package com.bkarakoca.vehicletrackingapp.domain.vehicle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.data.repository.VehicleRepositoryImpl
import com.google.android.gms.maps.model.LatLng
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetVehicleListUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var vehicleRepository: VehicleRepositoryImpl

    lateinit var useCase: GetVehicleListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetVehicleListUseCase(vehicleRepository)
    }

    @Test
    fun `when repository answers success`(): Unit = runBlocking {
        // given
        coEvery { vehicleRepository.fetchVehicleList(any(), any()) } coAnswers { flow { emit(mockResponse) } }

        // when
        useCase.fetchVehicleList(GetVehicleListUseCase.Params(mockk(), mockk())).collect {
            Assert.assertTrue(it.size == 3)
            Assert.assertTrue(it.first().fleetType == "TAXI")
            Assert.assertTrue(it.first().heading == 5.0)
        }
    }

    @Test
    fun `when item come up with TAXI fleet value should contain taxi icon`(): Unit = runBlocking {
        // given
        coEvery { vehicleRepository.fetchVehicleList(any(), any()) } coAnswers { flow { emit(mockResponse) } }

        // when
        useCase.fetchVehicleList(GetVehicleListUseCase.Params(mockk(), mockk())).collect {
            Assert.assertTrue(it.first().fleetType == "TAXI")
            Assert.assertTrue(it.first().drawableRes == R.drawable.ic_taxi)
        }
    }

    @Test
    fun `when item come up with POOLING fleet value should contain pooling icon`(): Unit = runBlocking {
        // given
        coEvery { vehicleRepository.fetchVehicleList(any(), any()) } coAnswers { flow { emit(mockResponse) } }

        // when
        useCase.fetchVehicleList(GetVehicleListUseCase.Params(mockk(), mockk())).collect {
            Assert.assertTrue(it[1].fleetType == "POOLING")
            Assert.assertTrue(it[1].drawableRes == R.drawable.ic_pooling)
        }
    }

    @Test
    fun `when item come up with unique fleet value should contain pooling icon`(): Unit = runBlocking {
        // given
        coEvery { vehicleRepository.fetchVehicleList(any(), any()) } coAnswers { flow { emit(mockResponse) } }

        // when
        useCase.fetchVehicleList(GetVehicleListUseCase.Params(mockk(), mockk())).collect {
            Assert.assertTrue(it[2].fleetType != "TAXI")
            Assert.assertTrue(it[2].fleetType != "POOLING")
            Assert.assertTrue(it[2].drawableRes == R.drawable.ic_default_vehicle)
        }
    }

    private val mockResponse = listOf(
        VehicleInfoUIModel(
            id = "1",
            location = LatLng(1.0, 1.0),
            fleetType = "TAXI",
            heading = 5.0,
            drawableRes = R.drawable.ic_taxi
        ),
        VehicleInfoUIModel(
            id = "2",
            location = LatLng(2.0, 2.0),
            fleetType = "POOLING",
            heading = 5.0,
            drawableRes = R.drawable.ic_pooling
        ),
        VehicleInfoUIModel(
            id = "3",
            location = LatLng(3.0, 3.0),
            fleetType = "UNIQUE",
            heading = 5.0,
            drawableRes = R.drawable.ic_default_vehicle
        )
    )

}