package com.daon.onjung.ui.mail

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.EventRepository
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val onjungRepository: OnjungRepository,
    private val eventRepository: EventRepository
) : BaseViewModel<MailContract.State, MailContract.Event, MailContract.Effect>(
    initialState = MailContract.State()
) {
    init {
        getOnjungCount()
        getMailList()
    }

    override fun reduceState(event: MailContract.Event) {
        when (event) {
            is MailContract.Event.LoadMoreMailList -> {
                getMailList()
            }
            is MailContract.Event.MailClicked -> {
                postEffect(MailContract.Effect.NavigateTo("${Routes.Home.SHOP_DETAIL}?shopId=${event.id}"))
            }
        }
    }

    private fun getMailList() {
        if (currentState.isMailListFetching || currentState.isMailListLastPage) return

        viewModelScope.launch {
            eventRepository.getOnjungMailList(currentState.mailListCurrentPage, currentState.mailListPageSize)
                .onStart {
                    updateState(
                        currentState.copy(
                            isLoading = true,
                            isMailListFetching = true
                        )
                    )
                }
                .collect {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            isMailListFetching = false
                        )
                    )
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                val updatedList = currentState.mailList.toMutableList()
                                val updatedPage = if (!currentState.isMailListLastPage) {
                                    currentState.mailListCurrentPage + 1
                                } else {
                                    currentState.mailListCurrentPage
                                }
                                updatedList.addAll(result.eventList)
                                updateState(
                                    currentState.copy(
                                        mailList = updatedList,
                                        mailListCurrentPage = updatedPage,
                                        isMailListLastPage = !result.hasNext
                                    )
                                )
                            }
                        }
                        is ApiResult.ApiError -> {
                            postEffect(MailContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(MailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun getOnjungCount() = viewModelScope.launch {
        onjungRepository.getOnjungCount().onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    it.data?.data?.let {
                        updateState(currentState.copy(onjungCount = it.totalOnjungCount))
                    }
                }
                is ApiResult.ApiError ->{
                    postEffect(MailContract.Effect.ShowSnackBar(it.message))
                }
                is ApiResult.NetworkError -> {
                    postEffect(MailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                }
            }
        }
    }
}