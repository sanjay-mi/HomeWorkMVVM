package com.imaginato.homeworkmvvm.exts

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.imaginato.homeworkmvvm.R

fun View?.showSnackBar(message: String?) {
    this ?: return
    if (!message.isNullOrEmpty()) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
            setBackgroundTint(ContextCompat.getColor(this.context, R.color.dark_blue))
            setTextColor(ContextCompat.getColor(this.context, R.color.white))
            show()
        }
    }
}