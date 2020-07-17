package com.innoventesmovistv.myapplication.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun ProgressBar.show() {
    visibility = View.VISIBLE
}


fun ProgressBar.hide() {
    visibility = View.INVISIBLE
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}



fun ProgressBar.toggle(context: Context) {
    visibility = if (visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.GONE
    }
}













