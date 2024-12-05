package com.daon.onjung.ui.home.shopdetail

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.StoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopDetailViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : BaseViewModel<ShopDetailContract.State, ShopDetailContract.Event, ShopDetailContract.Effect>(
    initialState = ShopDetailContract.State()
) {
    override fun reduceState(event: ShopDetailContract.Event) {
        when (event) {
            is ShopDetailContract.Event.ToggleExpand -> {
                updateIsExpanded(event.isExpanded)
            }

            is ShopDetailContract.Event.OnjungShareClicked -> {
                shareOnjung(event.id)
            }
        }
    }

    @Suppress("MissingPermission")
    fun fetchUserLocation(
        onSuccess: () -> Unit
    ) {
        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    updateState(
                        currentState.copy(
                            userPosition = Pair(latitude, longitude)
                        )
                    )
                    onSuccess()
                } else {
                    postEffect(ShopDetailContract.Effect.ShowSnackBar("위치를 가져올 수 없습니다."))
                }
            }.addOnFailureListener {
                postEffect(ShopDetailContract.Effect.ShowSnackBar("위치를 가져올 수 없습니다."))
            }
        } catch (e: Exception) {
            postEffect(ShopDetailContract.Effect.ShowSnackBar("예외 발생: ${e.message}"))
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

    private fun shareOnjung(id: Int) = viewModelScope.launch {
        storeRepository.putStoreOnjungShare(id)
            .onStart {
                updateState(currentState.copy(isLoading = true))
            }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        postEffect(ShopDetailContract.Effect.KakaoShare)
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

    private fun updateIsExpanded(isExpanded: Boolean) {
        updateState(currentState.copy(isExpanded = isExpanded))
    }
}