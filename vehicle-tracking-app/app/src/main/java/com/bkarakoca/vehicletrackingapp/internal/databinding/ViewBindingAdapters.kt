package com.bkarakoca.vehicletrackingapp.internal.databinding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bkarakoca.vehicletrackingapp.R
import com.bkarakoca.vehicletrackingapp.base.BaseListAdapter
import com.bkarakoca.vehicletrackingapp.base.ListAdapterItem
import com.bkarakoca.vehicletrackingapp.internal.extension.loadImage

@BindingAdapter("hideIfNull")
fun setVisible(view: View, obj: Any?) {
    view.visibility = if (obj == null) {
        View.GONE
    } else if (obj is String && obj.isBlank()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("visibleIf")
fun visibleIf(view: View, shouldVisible: Boolean) {
    view.visibility = if (shouldVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.submitList(list)
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: BaseListAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let {
        view.adapter = it
    }
}

@BindingAdapter("imageFromUrl", "placeholderRes", "errorRes", requireAll = false)
fun setImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?
) {
    val defaultDrawable = R.drawable.ic_launcher_background

    view.loadImage(
        url,
        placeholderRes ?: defaultDrawable,
        errorRes ?: defaultDrawable
    )
}
