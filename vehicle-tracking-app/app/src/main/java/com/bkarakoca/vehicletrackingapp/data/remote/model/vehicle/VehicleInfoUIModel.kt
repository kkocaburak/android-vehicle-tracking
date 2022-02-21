package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.bkarakoca.vehicletrackingapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleInfoUIModel(
    override val id: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fleetType: String? = null,
    val heading: Double? = null,
    @DrawableRes val imageRes: Int
) : Parcelable, ListAdapterItem