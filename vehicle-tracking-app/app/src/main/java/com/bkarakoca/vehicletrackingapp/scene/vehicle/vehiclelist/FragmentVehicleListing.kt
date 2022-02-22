package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclelist

import androidx.navigation.navGraphViewModels
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseFragment
import com.bkarakoca.vehicletrackingapp.databinding.FragmentVehicleListBinding
import com.bkarakoca.vehicletrackingapp.internal.extension.observe
import com.bkarakoca.vehicletrackingapp.scene.vehicle.VehicleSharedVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentVehicleListing : BaseFragment<FragmentVehicleListingVM, FragmentVehicleListBinding>() {

    override val layoutId = R.layout.fragment_vehicle_list

    private val vehicleSharedVM: VehicleSharedVM by navGraphViewModels(R.id.nav_vehicle_list)

    private val adapterVehicle = AdapterVehicleList()

    override fun initialize() {
        viewModel.fetchVehicleList()
        binder.recyclerViewVehicles.apply {
            adapter = adapterVehicle
            setHasFixedSize(true)
        }
    }

    override fun setListeners() {
        adapterVehicle.setOnClickListener { vehicleInfo ->
            vehicleSharedVM.selectedVehicle.value = vehicleInfo
            viewModel.onVehicleClicked()
        }
    }

    override fun setReceivers() {
        observe(viewModel.vehicleList) { vehicleInfoList ->
            vehicleSharedVM.vehicleList.value = vehicleInfoList
            adapterVehicle.submitList(vehicleInfoList)
        }
    }

}