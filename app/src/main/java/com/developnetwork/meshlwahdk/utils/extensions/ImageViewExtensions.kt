package com.developnetwork.meshlwahdk.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.developnetwork.meshlwahdk.R
import com.squareup.picasso.Picasso

fun AppCompatImageView.setImageURL(url: String, placeholder: Int= R.drawable.ic_image_placeholder) {
    Picasso.get().load(url).fit().placeholder(placeholder).into(this)
}