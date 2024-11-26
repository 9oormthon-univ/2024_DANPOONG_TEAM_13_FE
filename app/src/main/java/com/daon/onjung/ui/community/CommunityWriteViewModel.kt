package com.daon.onjung.ui.community

import android.net.Uri
import com.daon.onjung.util.BaseViewModel
import javax.inject.Inject

class CommunityWriteViewModel @Inject constructor(

) : BaseViewModel<CommunityWriteContract.State, CommunityWriteContract.Event, CommunityWriteContract.Effect>(
    initialState = CommunityWriteContract.State()
) {
    override fun reduceState(event: CommunityWriteContract.Event) {
        when (event) {
            is CommunityWriteContract.Event.SelectImage -> {

            }
            is CommunityWriteContract.Event.UploadPost -> {

            }
        }
    }

    fun updateSelectedImage(imageUri: Uri) {
        updateState(currentState.copy(selectedImgUri = imageUri))
    }

    fun updateTitle(title: String) {
        updateState(currentState.copy(title = title))
    }

    fun updateContent(content: String) {
        updateState(currentState.copy(content = content))
    }
}