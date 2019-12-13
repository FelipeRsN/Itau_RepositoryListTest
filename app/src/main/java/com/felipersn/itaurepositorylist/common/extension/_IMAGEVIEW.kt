package com.felipersn.itaurepositorylist.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.felipersn.itaurepositorylist.R

/**
 * Method to get image from internet and insert to imageView using glide
 */
fun ImageView.loadImageFromURL(url: String?) {
    url?.let {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .error(R.drawable.ic_account_primary)
            .thumbnail(0.1f)
            .into(this)
    } ?: run {
        this.setImageResource(R.drawable.ic_account_primary)
    }
}