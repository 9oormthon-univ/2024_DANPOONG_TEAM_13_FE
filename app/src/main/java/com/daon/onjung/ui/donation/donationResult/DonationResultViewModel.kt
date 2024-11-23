package com.daon.onjung.ui.donation.donationResult

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
class DonationResultViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : BaseViewModel<DonationResultContract.State, DonationResultContract.Event, DonationResultContract.Effect>(
    initialState = DonationResultContract.State()
) {
    override fun reduceState(event: DonationResultContract.Event) {}
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
                            postEffect(DonationResultContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(DonationResultContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }
}