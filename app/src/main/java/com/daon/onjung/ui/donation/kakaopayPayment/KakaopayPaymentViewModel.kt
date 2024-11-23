package com.daon.onjung.ui.donation.kakaopayPayment

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
class KakaopayPaymentViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : BaseViewModel<KakaopayPaymentContract.State, KakaopayPaymentContract.Event, KakaopayPaymentContract.Effect>(
    initialState = KakaopayPaymentContract.State()
) {
    override fun reduceState(event: KakaopayPaymentContract.Event) {
        when (event) {
            is KakaopayPaymentContract.Event.CheckBoxClicked -> {
                updateState(currentState.copy(isChecked = !event.isChecked))
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
                                    )
                                )
                            }
                        }
                        is ApiResult.ApiError -> {
                            postEffect(KakaopayPaymentContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(KakaopayPaymentContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }
}