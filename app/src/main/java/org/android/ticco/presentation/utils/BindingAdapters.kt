package org.android.ticco.presentation.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.android.ticco.R

object BindingAdapters{

    @JvmStatic
    @BindingAdapter("android:setProfileImage")
    fun ImageView.setProfile(url:String?){
        Glide.with(context)
            .load(url ?: "")
            .placeholder(R.drawable.ic_profile_circle)
            .into(this)
    }

}