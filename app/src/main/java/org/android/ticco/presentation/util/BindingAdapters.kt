package org.android.ticco.presentation.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.android.ticco.R


object BindingAdapters {

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
            .into(this)

    }

    @JvmStatic
    @BindingAdapter("android:categoryText")
    fun TextView.setTextCategory(category: String) {
        text = when (category) {
            "" -> context.getString(R.string.home_all)
            context.getString(R.string.home_musical_en) -> context.getString(R.string.home_musical)
            context.getString(R.string.home_theater_en) -> context.getString(R.string.home_theater)
            context.getString(R.string.home_movie_en) -> context.getString(R.string.home_movie)
            context.getString(R.string.home_concert_en) -> context.getString(R.string.home_concert)
            context.getString(R.string.home_exhibition_en) -> context.getString(R.string.home_exhibition)
            context.getString(R.string.home_festival_en) -> context.getString(R.string.home_festival)
            else -> context.getString(R.string.home_all)
        }
    }

    @JvmStatic
    @BindingAdapter("android:categoryTextFont")
    fun TextView.setTextFont(category: String?) {
        typeface = when (category) {
            "",
            context.getString(R.string.home_musical_en),
            context.getString(R.string.home_theater),
            context.getString(R.string.home_movie_en),
            context.getString(R.string.home_exhibition_en),
            context.getString(R.string.home_concert_en),
            context.getString(R.string.home_festival_en),
            context.getString(R.string.home_past),
            context.getString(R.string.home_high_score),
            context.getString(R.string.home_low_score) -> resources.getFont(R.font.pretendard_bold)
            else -> resources.getFont(R.font.pretendard_semibold)
        }
    }

}