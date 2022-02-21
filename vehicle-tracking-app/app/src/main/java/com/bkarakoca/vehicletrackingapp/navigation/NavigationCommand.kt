package com.bkarakoca.vehicletrackingapp.navigation

import androidx.navigation.NavDirections
import com.bkarakoca.vehicletrackingapp.internal.popup.PopupListener
import com.bkarakoca.vehicletrackingapp.internal.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
