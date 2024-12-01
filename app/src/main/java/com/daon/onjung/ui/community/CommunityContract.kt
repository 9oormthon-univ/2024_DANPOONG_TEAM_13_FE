package com.daon.onjung.ui.community

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.BoardDetail
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class CommunityContract {
    data class State(
        val isLoading: Boolean = false,
        val isBoardsListFetching: Boolean = false,
        val page: Int = 1,
        val pageSize: Int = 20,
        val posts: List<BoardDetail> = emptyList(),
        val isBoardsListLastPage: Boolean = false,
    ) : UiState

    sealed class Event : UiEvent {
        data class SelectPost(val postId : Int) : Event()
        data object LoadMorePosts : Event()
        data object RefreshPosts : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
    }

}