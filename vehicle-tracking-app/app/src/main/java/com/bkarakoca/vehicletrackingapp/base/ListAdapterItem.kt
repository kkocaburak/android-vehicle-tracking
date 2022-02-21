package com.bkarakoca.vehicletrackingapp.base

interface ListAdapterItem {
    val id: String?

    override fun equals(other: Any?): Boolean
}
