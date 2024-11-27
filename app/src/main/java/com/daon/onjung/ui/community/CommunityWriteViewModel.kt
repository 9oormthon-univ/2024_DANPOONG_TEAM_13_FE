package com.daon.onjung.ui.community

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.daon.onjung.Constants
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.SuggestionRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityWriteViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) : BaseViewModel<CommunityWriteContract.State, CommunityWriteContract.Event, CommunityWriteContract.Effect>(
    initialState = CommunityWriteContract.State()
) {
    override fun reduceState(event: CommunityWriteContract.Event) {
        when (event) {
            is CommunityWriteContract.Event.UploadPost -> {
                uploadPost()
            }
        }
    }

    private fun uploadPost() = viewModelScope.launch {
        suggestionRepository.postBoard(
            imageUri = currentState.selectedImgUri,
            title = currentState.title,
            content = currentState.content
        ).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    postEffect(CommunityWriteContract.Effect.ShowSnackBar("게시글이 성공적으로 등록되었습니다."))
                    // TODO : API 응답에 id 내려주면 게시글 상세 보기로 이동 예정
                    postEffect(CommunityWriteContract.Effect.NavigateTo(
                        destination = Routes.Community.ROUTE,
                        navOptions = navOptions {
                            popUpTo(Routes.Community.ROUTE) {
                                inclusive = true
                            }
                        }
                    ))
                }
                is ApiResult.ApiError -> {
                    postEffect(CommunityWriteContract.Effect.ShowSnackBar(it.message))
                }
                is ApiResult.NetworkError -> {
                    postEffect(CommunityWriteContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                }
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