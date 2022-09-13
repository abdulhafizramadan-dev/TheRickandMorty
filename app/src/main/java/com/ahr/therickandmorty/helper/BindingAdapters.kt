package com.ahr.therickandmorty.helper

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.ahr.therickandmorty.R

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

@BindingAdapter(value = ["status"])
fun setDrawableStatus(
    textView: TextView,
    status: String?
) {
    val resDrawable = when (status) {
        "Alive" -> R.drawable.ic_status_alive
        "Dead" -> R.drawable.ic_status_dead
        else -> R.drawable.ic_status_unknown
    }
    val statusDrawable = ContextCompat.getDrawable(textView.context, resDrawable)
    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(statusDrawable, null, null, null)
}