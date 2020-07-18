package com.innoventesmovistv.myapplication.utils

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("setImage")
fun setImage(view: SimpleDraweeView, url: String?) {
    val image = Constant.IMAGE_BASE_URL + Constant.IMAGE_FILE_SIZE + url

    if (null != image) {
        val uri = Uri.parse(image)
        view.setImageURI(uri)
    }
}