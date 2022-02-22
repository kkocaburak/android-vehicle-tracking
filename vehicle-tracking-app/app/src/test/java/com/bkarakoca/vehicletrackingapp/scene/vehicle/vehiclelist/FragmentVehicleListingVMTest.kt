package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.domain.vehicle.GetVehicleListUseCase
import com.bkarakoca.vehicletrackingapp.internal.util.Failure
import com.google.android.gms.maps.model.LatLng
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class FragmentVehicleListingVMTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var vehicleListUseCase: GetVehicleListUseCase

    lateinit var viewModel: FragmentVehicleListingVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = FragmentVehicleListingVM(vehicleListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchVehicleList success should post VehicleInfoUIModel`() = runBlocking {
        // given
        coEvery { vehicleListUseCase.fetchVehicleList(any()) } coAnswers { flow { emit(mockResponse) } }

        // when
        viewModel.fetchVehicleList()

        // then
        Assert.assertTrue(viewModel.vehicleList.value != null)
    }

    @Test
    fun `when fetchVehicleList failure should not post VehicleInfoUIModel`(): Unit = runBlocking {
        // given
        coEvery { vehicleListUseCase.fetchVehicleList(any()) } coAnswers { flow { throw Exception()} }

        // when
        viewModel.fetchVehicleList()

        // then
        Assert.assertTrue(viewModel.vehicleList.value == null)
    }

    @Test
    fun `when fetchVehicleList failure should post value to popup`(): Unit = runBlocking {
        val customFailure = Failure.CustomException("test")

        // given
        coEvery { vehicleListUseCase.fetchVehicleList(any()) } coAnswers { flow { throw customFailure } }

        // when
        viewModel.fetchVehicleList()

        // then
        Assert.assertTrue(viewModel.popup.value != null)
    }

    @Test
    fun `when onVehicleClicked should navigate vehicle map fragment`() {
        // when
        viewModel.onVehicleClicked()

        // then
        Assert.assertTrue(viewModel.navigation.value != null)
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
            fleetType = "TAXI",
            heading = 5.0,
            drawableRes = R.drawable.ic_taxi
        )
    )

}