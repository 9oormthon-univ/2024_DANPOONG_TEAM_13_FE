package com.daon.onjung.ui.donation.donation

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
class DonationViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
) : BaseViewModel<DonationContract.State, DonationContract.Event, DonationContract.Effect>(
    initialState = DonationContract.State()
) {
    override fun reduceState(event: DonationContract.Event) {
        when (event) {
            is DonationContract.Event.AmountChangedClicked -> {
                updateState(currentState.copy(amount = currentState.amount + event.price))
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
                            postEffect(DonationContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(DonationContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }
}