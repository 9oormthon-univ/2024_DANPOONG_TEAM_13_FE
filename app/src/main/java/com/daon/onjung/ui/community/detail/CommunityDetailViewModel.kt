package com.daon.onjung.ui.community.detail

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.SuggestionRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityDetailViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) : BaseViewModel<CommunityDetailContract.State, CommunityDetailContract.Event, CommunityDetailContract.Effect>(
    initialState = CommunityDetailContract.State()
) {
    init {
        getCommentList()
    }

    override fun reduceState(event: CommunityDetailContract.Event) {
        when (event) {
            is CommunityDetailContract.Event.LoadMoreCommentList -> {
                getCommentList()
            }
        }
    }

    fun getBoardDetail(id: Int) = viewModelScope.launch {
        suggestionRepository.getBoardDetail(id)
            .onStart {
                updateState(
                    currentState.copy(
                        isLoading = true,
                        boardId = id
                    )
                )
            }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.data?.let { result ->
                            updateState(currentState.copy(
                                writerInfo = result.writerInfo,
                                boardInfo = result.boardInfo,
                            ))
                        }
                    }
                    is ApiResult.ApiError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(it.message))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
    }

    private fun getCommentList() {
        if (currentState.isCommentListFetching || currentState.isCommentListLastPage) return

        viewModelScope.launch {
            suggestionRepository.getCommentList(currentState.boardInfo.id, currentState.commentListCurrentPage, currentState.commentListPageSize)
                .onStart {
                    updateState(
                        currentState.copy(
                            isLoading = true,
                            isCommentListFetching = true
                        )
                    )
                }
                .collect {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            isCommentListFetching = false
                        )
                    )
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                val updatedList = currentState.commentList.toMutableList()
                                val updatedPage = if (!currentState.isCommentListLastPage) {
                                    currentState.commentListCurrentPage + 1
                                } else {
                                    currentState.commentListCurrentPage
                                }
                                updatedList.addAll(result.commentList)
                                updateState(currentState.copy(
                                    commentList = updatedList,
                                    isCommentListLastPage = !result.hasNext,
                                    commentListCurrentPage = updatedPage,
                                ))
                            }
                        }
                        is ApiResult.ApiError -> {
                            postEffect(CommunityDetailContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(CommunityDetailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun postLikeBoard(id: Int) = viewModelScope.launch {
        suggestionRepository.postLikeBoard(id)
            .onStart { updateState(currentState.copy(isLoading = true)) }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.data?.let {
                            updateState(
                                currentState.copy(
                                    boardInfo = currentState.boardInfo.copy(
                                        isLiked = true,
                                    )
                                )
                            )
                        }
                    }

                    is ApiResult.ApiError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(it.message))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
    }

    private fun postComment() = viewModelScope.launch {
        suggestionRepository.postComment(
            id = currentState.boardInfo.id,
            content = currentState.commentInput
        ).onStart { updateState(currentState.copy(isLoading = true)) }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        /*
                        updateState(currentState.copy(
                            commentList = listOf(it.data?.data?.commentDetail) + currentState.commentList
                        ))*/
                    }
                    is ApiResult.ApiError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(it.message))
                    }
                    is ApiResult.NetworkError -> {
                        postEffect(CommunityDetailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
    }

    fun updateCommentInput(commentInput: String)  {
        updateState(currentState.copy(commentInput = commentInput))
    }
}