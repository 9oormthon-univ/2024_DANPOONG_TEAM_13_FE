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
class ProfileListViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : BaseViewModel<ProfileListContract.State, ProfileListContract.Event, ProfileListContract.Effect>(
    initialState = ProfileListContract.State()
) {
    init {
        getTicketList()
    }

    override fun reduceState(event: ProfileListContract.Event) {
        when (event) {
            is ProfileListContract.Event.LoadMoreTicketList -> {
                getTicketList()
            }

            is ProfileListContract.Event.MealTicketClicked -> {

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
                            postEffect(ProfileListContract.Effect.ShowSnackBar(it.message))
                        }

                        is ApiResult.NetworkError -> {
                            postEffect(ProfileListContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

}