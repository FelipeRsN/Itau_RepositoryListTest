package com.felipersn.itaurepositorylist.common.extension

import android.widget.TextView

/**
 * Method to handler null texts received to textView
 */
fun TextView.addText(text: String?) {
    text?.let {
        this.text = it
    } ?: run {
        this.text = ""
    }
}