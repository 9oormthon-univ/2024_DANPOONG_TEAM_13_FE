package com.daon.onjung.ui.component.imagepicker

import android.content.Context
import android.net.Uri

internal class ImageRepository(
    val context: Context
) {
    fun getImages(): List<ImageInfo> {
        return ImageLoader.load(context)
    }

    fun insertImage(): Uri? {
        return ImageLoader.insertImage(context)
    }

    fun deleteImage(uri: Uri?) {
        uri?.let {
            ImageLoader.deleteImage(context, uri)
        }
    }
}