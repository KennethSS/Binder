package com.solar.binder

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("clipOutline")
fun setClipOutline(iv: AppCompatImageView, clipToOutline: Boolean?) {
    clipToOutline?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.clipToOutline = clipToOutline
        }
    }
}

@BindingAdapter("android:src")
fun setImageResource(iv: AppCompatImageView, @DrawableRes resId: Int?) {
    resId?.let {
        iv.setImageResource(it)
    }
}