package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclemap

import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseFragment
import com.bkarakoca.vehicletrackingapp.databinding.FragmentVehicleMapBinding
import com.bkarakoca.vehicletrackingapp.internal.extension.observe
import com.bkarakoca.vehicletrackingapp.internal.service.*
import com.bkarakoca.vehicletrackingapp.internal.util.DeviceServiceManager
import com.bkarakoca.vehicletrackingapp.scene.vehicle.VehicleSharedVM
import com.google.android.gms.maps.model.Marker

class FragmentVehicleMap : BaseFragment<FragmentVehicleMapVM, FragmentVehicleMapBinding>(),
    MapInitializedListener, MapItemClickListener {

    override val layoutId = R.layout.fragment_vehicle_map

    private val vehicleSharedVM: VehicleSharedVM by navGraphViewModels(R.id.nav_vehicle_list)

    lateinit var mapService: MapService

    override fun initialize() {
        initializeMap()
    }

    private fun initializeMap() {
        mapService = DeviceServiceManager.provideMapService()
        lifecycleScope.launchWhenCreated {
            mapService.apply {
                onCreate(
                    activity = requireActivity(),
                    containerResourceId = R.id.map_fragment,
                    mapInitializedListener = this@FragmentVehicleMap,
                    itemClickListener = this@FragmentVehicleMap
                )
                setMaxZoomPreference(MapConstants.MAX_ZOOM_LEVEL)
            }
        }
    }

    override fun onClick(marker: Marker): Boolean {
        // TODO :
        return true
    }

    override fun onMapInitialized() {
        viewModel.fetchVehicleMap(vehicleSharedVM.vehicleList.value)
    }

    override fun setReceivers() {
        observe(viewModel.vehicleMarkerOptions) { markerOptions ->
            markerOptions?.let {
                mapService.addMarkers(it)
            }
            mapService.animateCamera(vehicleSharedVM.selectedVehicle.value?.location, MapConstants.NORMAL_ZOOM_LEVEL)
        }
    }

    override fun onDestroyView() {
        mapService.destroyMap()
        super.onDestroyView()
    }
}