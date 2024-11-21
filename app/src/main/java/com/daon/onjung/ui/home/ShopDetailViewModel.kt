package com.daon.onjung.ui.home

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.StoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopDetailViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
) : BaseViewModel<ShopDetailContract.State, ShopDetailContract.Event, ShopDetailContract.Effect>(
    initialState = ShopDetailContract.State()
) {
    override fun reduceState(event: ShopDetailContract.Event) {
        when (event) {
            is ShopDetailContract.Event.ToggleExpand -> {
                updateIsExpanded(event.isExpanded)
            }
        }
    }

    fun getStoreDetailInfo(id: Int) {
        viewModelScope.launch {
            storeRepository.getStoreDetail(id)
                .onStart {
                    updateState(currentState.copy(isLoading = true))
                }
                .collect {
                    updateState(currentState.copy(isLoading = false))
                    when (it) {
                        is ApiResult.Success -> {
                            it.data?.data?.let { result ->
                                updateState(
                                    currentState.copy(
                                        storeInfo = result.storeInfo,
                                        eventInfo = result.eventInfo,
                                        onjungInfo = result.onjungInfo,
                                        storeHistories = result.storeHistories
                                    )
                                )
                            }
                        }
                        is ApiResult.ApiError -> {
                            postEffect(ShopDetailContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(ShopDetailContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun updateIsExpanded(isExpanded: Boolean) {
        updateState(currentState.copy(isExpanded = isExpanded))
    }
}