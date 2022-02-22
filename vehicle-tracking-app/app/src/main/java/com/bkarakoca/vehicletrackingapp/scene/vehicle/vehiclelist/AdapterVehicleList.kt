package com.bkarakoca.vehicletrackingapp.scene.vehicle.vehiclelist

import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseListAdapter
import com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle.VehicleInfoUIModel
import com.bkarakoca.vehicletrackingapp.databinding.ItemVehicleInfoBinding
import com.bkarakoca.vehicletrackingapp.internal.extension.executeAfter

class AdapterVehicleList : BaseListAdapter<ItemVehicleInfoBinding, VehicleInfoUIModel>() {

    private var onVehicleClickListener: ((VehicleInfoUIModel) -> Unit)? = null

    override val layoutRes = R.layout.item_vehicle_info

    override fun bind(binding: ItemVehicleInfoBinding, item: VehicleInfoUIModel) {
        binding.executeAfter {
            vehicleItem = item
        }
        binding.cardViewRoot.setOnClickListener {
            onVehicleClickListener?.invoke(item)
        }
    }

    fun setOnClickListener(onVehicleClickListener: ((VehicleInfoUIModel) -> Unit)?) {
        this.onVehicleClickListener = onVehicleClickListener
    }
}