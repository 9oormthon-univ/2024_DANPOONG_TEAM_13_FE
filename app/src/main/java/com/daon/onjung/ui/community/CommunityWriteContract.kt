package com.daon.onjung.ui.community

import android.net.Uri
import androidx.navigation.NavOptions
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class CommunityWriteContract {

    data class State(
        val isLoading: Boolean = false,
        val selectedImgUri: Uri? = null,
        val titlePlaceholderText: String = "제목을 입력해주세요",
        val contentPlaceholderText: String = "가게의 주소지와 선행을 입력해 주세요 :)",
        val title: String = "",
        val content: String = "",
        val titleMaxLength: Int = 20,
        val contentMaxLength: Int = 200
    ) : UiState

    sealed class Event : UiEvent {
        data class SelectImage(val imgUrl: String) : Event()
        data class UploadPost(val imgUrl: String?, val title: String, val content: String) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
    }

}