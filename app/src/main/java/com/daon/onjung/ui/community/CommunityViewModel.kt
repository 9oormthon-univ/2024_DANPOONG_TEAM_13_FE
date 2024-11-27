package com.daon.onjung.ui.community

import androidx.lifecycle.viewModelScope
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
class CommunityViewModel @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) : BaseViewModel<CommunityContract.State, CommunityContract.Event, CommunityContract.Effect>(
    initialState = CommunityContract.State()
) {
    override fun reduceState(event: CommunityContract.Event) {
        when (event) {
            is CommunityContract.Event.SelectPost -> {
                postEffect(CommunityContract.Effect.NavigateTo(
                    "${Routes.Community.DETAIL}/${event.postId}",
                ))
            }
            is CommunityContract.Event.LoadPosts -> {
                getPosts()
            }
        }
    }

    init {
        getPosts()
    }

    private fun getPosts() {
        if (currentState.isBoardsListFetching || currentState.isBoardsListLastPage) return

        viewModelScope.launch {
            suggestionRepository.getBoards(currentState.pageSize, currentState.page)
                .onStart {
                    updateState(
                        currentState.copy(
                            isLoading = true
                        )
                    )
                }
                .collect {
                    updateState(
                        currentState.copy(
                            isLoading = false
                        )
                    )
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                val updateList = currentState.posts.toMutableList()
                                val updatedPage = if (!currentState.isBoardsListLastPage) {
                                    currentState.page + 1
                                } else {
                                    currentState.page
                                }
                                updateList.addAll(result.boardList)
                                updateState(currentState.copy(
                                    posts = updateList,
                                    isBoardsListLastPage = !result.hasNext,
                                    page = updatedPage
                                ))
                                if (result.hasNext) {
                                    updateState(currentState.copy(page = currentState.page + 1))
                                }
                            }
                        }

                        is ApiResult.ApiError -> {
                            postEffect(CommunityContract.Effect.ShowSnackBar(it.message))
                        }

                        is ApiResult.NetworkError -> {
                            postEffect(CommunityContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }


}