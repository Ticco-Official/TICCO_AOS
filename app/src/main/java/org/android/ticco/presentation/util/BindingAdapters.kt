package org.android.ticco.presentation.util

import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
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

    @JvmStatic
    @BindingAdapter("underline")
    fun TextView.setUnderline(isUnderLined: Boolean) {
        if (isUnderLined) {
            val spannable = SpannableString(this.text)
            spannable.setSpan(UnderlineSpan(), 0, spannable.length, 0)
            text = spannable
        }
    }
}