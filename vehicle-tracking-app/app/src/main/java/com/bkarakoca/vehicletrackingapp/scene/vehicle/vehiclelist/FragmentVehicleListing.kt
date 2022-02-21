package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclelist

import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseFragment
import com.bkarakoca.vehicletrackingapp.databinding.FragmentVehicleListBinding
import com.bkarakoca.vehicletrackingapp.internal.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentVehicleListing : BaseFragment<FragmentVehicleListingVM, FragmentVehicleListBinding>() {

    override val layoutId = R.layout.fragment_vehicle_list

    private val adapterVehicle = AdapterVehicleList()

    override fun initialize() {
        viewModel.fetchVehicleList()
        binder.recyclerViewVehicles.adapter = adapterVehicle
    }

    override fun setListeners() {
        adapterVehicle.setOnClickListener { vehicleInfo ->
            viewModel.onVehicleClicked(vehicleInfo)
        }
    }

    override fun setReceivers() {
        observe(viewModel.vehicleList) {
            adapterVehicle.submitList(it)
        }
    }

}