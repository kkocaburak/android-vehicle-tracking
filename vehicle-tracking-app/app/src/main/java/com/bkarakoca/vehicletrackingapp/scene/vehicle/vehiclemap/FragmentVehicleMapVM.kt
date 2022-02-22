package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclemap

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.vehicletrackingapp.base.BaseViewModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.getMapLocation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentVehicleMapVM @Inject constructor(): BaseViewModel() {

    var vehicleMarkerOptions = MutableLiveData<List<MarkerOptions>?>(null)

    fun fetchVehicleMap(vehicleList: List<VehicleInfoUIModel?>?) {
        vehicleMarkerOptions.value = vehicleList?.map { vehicleInfo ->
            vehicleInfo?.let {
                MarkerOptions()
                    .title(it.fleetType)
                    .position(it.getMapLocation())
                    .icon(BitmapDescriptorFactory.fromResource(it.drawableRes))
            } ?: MarkerOptions()
        }
    }

}