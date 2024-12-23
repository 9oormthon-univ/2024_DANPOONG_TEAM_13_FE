package com.daon.onjung.ui.home.ocr

import android.net.Uri
import androidx.navigation.NavOptions
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class OcrCameraContract {

    data class State(
        val isLoading: Boolean = false,
        val isGuideTextVisible: Boolean = true,
        val storeName: String = "",
        val storeAddress: String = "",
        val paymentDate: String = "",
        val paymentAmount: String = "",
        val isOcrErrorDialogVisible: Boolean = false,
        val isOcrPostErrorDialogVisible: Boolean = false,
        val isPostReceiptSuccessDialogVisible: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class ImageCaptured(val uri: Uri) : Event()
        data class PostReceiptButtonClicked(
            val storeName: String,
            val storeAddress: String,
            val paymentDate: String,
            val paymentAmount: String
        ) : Event()
        data object OcrErrorDialogDismissed : Event()
        data object OcrPostErrorDialogDismissed: Event()
        data object PostReceiptSuccessDialogDismissed : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
        data class ShowOcrResult(
            val storeName: String,
            val storeAddress: String,
            val paymentDate: String,
            val paymentAmount: String
        ) : Effect()
        data object CameraResume : Effect()
    }

}