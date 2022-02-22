package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.vehicletrackingapp.base.BaseViewModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.LocationModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.domain.vehicle.GetVehicleListUseCase
import com.bkarakoca.vehicletrackingapp.internal.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FragmentVehicleListingVM @Inject constructor(
    private val getVehicleListUseCase: GetVehicleListUseCase
) : BaseViewModel() {

    private val _vehicleList = MutableLiveData<List<VehicleInfoUIModel>>()
    val vehicleList: LiveData<List<VehicleInfoUIModel>> get() = _vehicleList

    private val firstCoordinate = LocationModel(
        latitude = 53.694865,
        longitude = 9.757589
    )

    private val secondCoordinate = LocationModel(
        latitude = 53.394655,
        longitude = 10.099891
    )

    fun fetchVehicleList() = launch {
        getVehicleListUseCase.fetchVehicleList(
            GetVehicleListUseCase.Params(
                firstCoordinate,
                secondCoordinate
            )
        ).collect {
            _vehicleList.value = it
        }
    }

    fun onVehicleClicked() {
        navigate(FragmentVehicleListingDirections.toFragmentVehicleMap())
    }

}