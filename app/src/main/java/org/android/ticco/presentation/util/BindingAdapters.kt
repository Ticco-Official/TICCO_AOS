package org.android.ticco.presentation.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.android.ticco.R

object BindingAdapters{

    @JvmStatic
    @BindingAdapter("android:profileImgUri")
    fun ImageView.setProfileImg(imgUri: Uri?) {
            Glide.with(this.context)
                .load(imgUri)
                .placeholder(R.drawable.ic_profile_circle)
                .error(R.drawable.ic_profile_circle)
                .circleCrop()
                .into(this)

    }

    @JvmStatic
    @BindingAdapter("android:ticketImgUri")
    fun ImageView.setTicketImg(imgUri: String?) {
        Glide.with(this.context)
            .load(imgUri)
            .placeholder(R.drawable.img_welcome)
            .error(R.drawable.img_welcome)
            .circleCrop()
            .into(this)

    }

}