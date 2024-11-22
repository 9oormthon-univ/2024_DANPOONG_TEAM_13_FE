package com.daon.onjung.ui.profile

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.EventRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileTicketListViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : BaseViewModel<ProfileTicketListContract.State, ProfileTicketListContract.Event, ProfileTicketListContract.Effect>(
    initialState = ProfileTicketListContract.State()
) {
    init {
        getTicketList()
    }

    override fun reduceState(event: ProfileTicketListContract.Event) {
        when (event) {
            is ProfileTicketListContract.Event.LoadMoreTicketList -> {
                getTicketList()
            }

            is ProfileTicketListContract.Event.MealTicketClicked -> {
                getQrCode(event.id)
            }
        }
    }

    private fun getTicketList() {
        if (currentState.isTicketListFetching || currentState.isTicketListLastPage) return

        viewModelScope.launch {
            eventRepository.getTicketList(currentState.ticketListCurrentPage, currentState.ticketListPageSize)
                .onStart {
                    updateState(
                        currentState.copy(
                            isLoading = true,
                            isTicketListFetching = true
                        )
                    )
                }
                .collect {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            isTicketListFetching = false
                        )
                    )
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                val updatedList = currentState.ticketList.toMutableList()
                                val updatedPage = if (!currentState.isTicketListLastPage) {
                                    currentState.ticketListCurrentPage + 1
                                } else {
                                    currentState.ticketListCurrentPage
                                }
                                updatedList.addAll(result.ticketList)
                                updateState(
                                    currentState.copy(
                                        ticketList = updatedList,
                                        isTicketListLastPage = !result.hasNext,
                                        ticketListCurrentPage = updatedPage
                                    )
                                )
                            }
                        }

                        is ApiResult.ApiError -> {
                            postEffect(ProfileTicketListContract.Effect.ShowSnackBar(it.message))
                        }

                        is ApiResult.NetworkError -> {
                            postEffect(ProfileTicketListContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun getQrCode(id: Int) = viewModelScope.launch {
        eventRepository.getTicketBrief(id = id).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    it.data?.data?.let { result ->
                        val selectedTicket = currentState.ticketList.find { it.id == id }
                        selectedTicket?.let { ticket ->
                            postEffect(
                                ProfileTicketListContract.Effect.ShowTicketBottomSheet(
                                    ticket.storeInfo.name,
                                    ticket.storeInfo.address,
                                    result.qrBase64,
                                    ticket.expirationDate
                                )
                            )
                        }
                    }
                }

                is ApiResult.ApiError -> {
                    postEffect(ProfileTicketListContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(ProfileTicketListContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                }
            }
        }
    }

}