package com.daon.onjung.ui.component.imagepicker

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

internal class ImageViewModel(
    private val repository: ImageRepository
) : ViewModel() {
    private val _images = mutableStateListOf<ImageInfo>()
    val images = _images

    private val _selectedImages = mutableStateListOf<ImageInfo>()
    val selectedImages = _selectedImages

    init {
        loadImages()
    }

    fun loadImages() {
        _images.clear()
        _images.addAll(repository.getImages())
    }

    fun insertImage(): Uri? {
        return repository.insertImage()
    }

    fun selectImageFromUri(uri: Uri, maxImgCount: Int) {
        val newImage = ImageLoader.getImageInfoFromUri(context = repository.context, uri = uri)
        newImage?.let { imageInfo ->
            _images.add(0, imageInfo)
            selectImage(imageInfo, maxImgCount)
        }
    }

    fun deleteImage(cameraUri: Uri?) {
        repository.deleteImage(cameraUri)
    }

    fun selectImage(image: ImageInfo, maxImgCount: Int) {
        if (maxImgCount == 1) {
            _selectedImages.clear()
            _selectedImages.add(image)
        } else {
            _selectedImages.add(image)
        }
    }

    fun removeImage(image: ImageInfo) {
        _selectedImages.remove(image)
    }

    fun clearImages() {
        _selectedImages.clear()
    }
}