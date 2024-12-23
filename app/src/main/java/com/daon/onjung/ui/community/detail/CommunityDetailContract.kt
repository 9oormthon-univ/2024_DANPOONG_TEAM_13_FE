package com.daon.onjung.ui.community.detail

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.CommunityPostStatus
import com.daon.onjung.network.model.response.BoardDetailBoardInfo
import com.daon.onjung.network.model.response.BoardDetailWriterInfo
import com.daon.onjung.network.model.response.CommentDetail
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class CommunityDetailContract {
    data class State(
        val isLoading: Boolean = false,
        val writerInfo: BoardDetailWriterInfo = BoardDetailWriterInfo(
            profileImgUrl = "",
            maskedNickname = "",
            isMe = false
        ),
        val boardInfo: BoardDetailBoardInfo = BoardDetailBoardInfo(
            id = 0,
            imgUrl = "",
            title = "",
            content = "",
            status = CommunityPostStatus.IN_PROGRESS,
            postedAgo = "",
            goalCount = 100,
            likeCount = 0,
            commentCount = 0,
            isLiked = false,
            startDate = "2024.12.02",
            endDate = "2024.01.01",
            remainingDays = 30
        ),
        val isCommentListFetching: Boolean = false,
        val isCommentListLastPage: Boolean = false,
        val commentListCurrentPage: Int = 1,
        val commentListPageSize: Int = 20,
        val commentList: List<CommentDetail> = emptyList(),
        val commentInput: String = ""
    ) : UiState

    sealed class Event : UiEvent {
        data object LoadMoreCommentList : Event()
        data object PostComment : Event()
        data object ToggleLike : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
        data object ScrollToLastItem : Effect()
    }
}