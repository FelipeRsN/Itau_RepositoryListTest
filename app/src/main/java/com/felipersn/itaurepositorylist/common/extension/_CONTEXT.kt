package com.felipersn.itaurepositorylist.common.extension

import android.content.Context
import android.widget.Toast

/**
 * Method to instantiate toast
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Method to instantiate long toast
 */
fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}