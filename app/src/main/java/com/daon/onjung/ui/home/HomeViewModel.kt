package com.daon.onjung.ui.home

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.data.repository.StoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val onjungRepository: OnjungRepository
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    init {
        getStoreList()
        getOnjungSummary()
    }

    override fun reduceState(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.LoadMoreStoreList -> {
                getStoreList()
            }
        }
    }

    private fun getStoreList() {
        if (currentState.isStoreListFetching || currentState.isStoreListLastPage) return

        viewModelScope.launch {
            storeRepository.getStoreList(currentState.storeListCurrentPage, currentState.storeListPageSize)
                .onStart {
                    updateState(
                        currentState.copy(
                            isLoading = true,
                            isStoreListFetching = true
                        )
                    )
                }
                .collect {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            isStoreListFetching = false
                        )
                    )
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                val updatedList = currentState.storeList.toMutableList()
                                val updatedPage = if (!currentState.isStoreListLastPage) {
                                    currentState.storeListCurrentPage + 1
                                } else {
                                    currentState.storeListCurrentPage
                                }
                                updatedList.addAll(result.storeList)
                                updateState(
                                    currentState.copy(
                                        storeList = updatedList,
                                        isStoreListLastPage = !result.hasNext,
                                        storeListCurrentPage = updatedPage
                                    )
                                )
                            }
                        }
                        is ApiResult.ApiError ->{
                            postEffect(HomeContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(HomeContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun getOnjungSummary() = viewModelScope.launch {
        onjungRepository.getOnjungSummary()
            .onStart { updateState(currentState.copy(isLoading = true)) }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.data?.let { result ->
                            updateState(currentState.copy(onjungSummary = result))
                        }
                    }
                    is ApiResult.ApiError -> {
                        postEffect(HomeContract.Effect.ShowSnackBar(it.message))
                    }
                    is ApiResult.NetworkError -> {
                        postEffect(HomeContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
        }

    fun updateIsStoreListFetching(isStoreListFetching: Boolean) {
        updateState(currentState.copy(isStoreListFetching = isStoreListFetching))
    }

    fun updateStoreListLastPage(isStoreListLastPage: Boolean) {
        updateState(currentState.copy(isStoreListLastPage = isStoreListLastPage))
    }

    fun updateStoreListCurrentPage(storeListCurrentPage: Int) {
        updateState(currentState.copy(storeListCurrentPage = storeListCurrentPage))
    }
}