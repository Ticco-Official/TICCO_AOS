package org.android.ticco.presentation.util

import android.graphics.Typeface
import android.net.Uri
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
                .placeholder(org.android.ticco.R.drawable.ic_profile_circle)
                .error(org.android.ticco.R.drawable.ic_profile_circle)
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
    @BindingAdapter("android:categoryCheckColor")
    fun ImageView.setCheckColor(category: String) {
        setImageResource(when(category){
            "","musical","theater","movie" -> R.drawable.ic_check_pink
            else -> R.drawable.ic_check_pink
        })
    }

    @JvmStatic
    @BindingAdapter("android:categoryText")
    fun TextView.setTextCategory(category: String) {
        text = when(category){
            "" -> "전체"
            "musical" ->"뮤지컬"
            "theater" -> "연극"
            "movie" -> "영화"
            "exhibition" -> "전시회"
            "concert" ->"콘서트"
            "festival" -> "페스티벌"
            else -> "전체"
        }
    }

    @JvmStatic
    @BindingAdapter("android:categoryTextFont")
    fun TextView.setTextFont(category: String) {
        typeface = when(category){
            "","musical","theater","movie","exhibition","concert","festival" -> resources.getFont(R.font.pretendard_bold)
            else -> resources.getFont(R.font.pretendard_semibold)
        }
    }

}