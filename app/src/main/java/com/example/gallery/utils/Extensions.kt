package com.example.gallery.utils

import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    Picasso.get()
        .load(url)
        .resize(325, 400)
        .into(this)
}

fun ImageView.loadFitImage(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .into(this)
}

fun EditText.getTextEt(): String {
    return text.toString().trim()
}

fun EditText.isEmptyEt(): Boolean {
    if (getTextEt().isEmpty()) {
        error = "Вставьте Url картинки!"
        return false
    }
    return true
}

