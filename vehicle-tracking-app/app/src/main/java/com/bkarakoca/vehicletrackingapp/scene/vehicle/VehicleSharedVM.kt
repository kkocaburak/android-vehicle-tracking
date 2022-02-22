package com.bkarakoca.vehicletrackingapp.scene.vehicle

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.vehicletrackingapp.base.BaseViewModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleSharedVM @Inject constructor() : BaseViewModel() {
    var selectedVehicle = MutableLiveData<VehicleInfoUIModel?>()
    var vehicleList = MutableLiveData<List<VehicleInfoUIModel?>>()
}