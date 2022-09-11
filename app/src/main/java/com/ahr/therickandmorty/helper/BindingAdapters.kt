package com.ahr.therickandmorty.helper

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter(value = ["imageUrl", "placeholder"])
fun setImageUrl(
    imageView: ImageView,
    url: String?,
    placeholder: Drawable?
) {
    imageView.load(url) {
        crossfade(true)
        placeholder(placeholder)
    }
}