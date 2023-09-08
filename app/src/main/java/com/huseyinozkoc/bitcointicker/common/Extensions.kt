package com.huseyinozkoc.bitcointicker.common

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 1500).show()
}