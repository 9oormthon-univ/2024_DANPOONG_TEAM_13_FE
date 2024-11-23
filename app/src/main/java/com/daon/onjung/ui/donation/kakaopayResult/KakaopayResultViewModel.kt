package com.daon.onjung.ui.donation.kakaopayResult

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.request.DonationRequest
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class KakaopayResultViewModel @Inject constructor(
    private val onjungRepository: OnjungRepository,
) : BaseViewModel<KakaopayResultContract.State, KakaopayResultContract.Event, KakaopayResultContract.Effect>(
    initialState = KakaopayResultContract.State()
) {
    override fun reduceState(event: KakaopayResultContract.Event) {
        when (event) {
            is KakaopayResultContract.Event.DonationCompleteDialogDismissed -> {
                updateState(currentState.copy(isDonationCompleteDialogVisible = false))
            }
            is KakaopayResultContract.Event.DonationCompleteClicked -> {
                postDonation(event.shopId, event.amount)
                updateState(currentState.copy(isDonationCompleteDialogVisible = true))
            }
        }
    }

    private fun postDonation(id: Int, amount: Int) {
        viewModelScope.launch {
            onjungRepository.postDonation(id, donationRequest = DonationRequest(amount))
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
                                        issueDate = result.issueDate,
                                        donationStoreInfo = result.storeInfo
                                    )
                                )
                            }
                        }
                        is ApiResult.ApiError -> {
                            postEffect(KakaopayResultContract.Effect.ShowSnackBar(it.message))
                        }
                        is ApiResult.NetworkError -> {
                            postEffect(KakaopayResultContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

}