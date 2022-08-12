package org.android.ticco.presentation.utils

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import org.android.ticco.presentation.home.TicketVpAdapter

@SuppressLint("NotifyDataSetChanged")
@BindingAdapter("setItems")
fun setAdapterItems(viewPager2: ViewPager2,items: ArrayList<Int>?) {
    items?.let {
        (viewPager2.adapter as TicketVpAdapter).apply {
            item = it
            notifyDataSetChanged()
        }
    }
}