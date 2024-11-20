package com.daon.onjung.ui.mail

import androidx.lifecycle.viewModelScope
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val onjungRepository: OnjungRepository
) : BaseViewModel<MailContract.State, MailContract.Event, MailContract.Effect>(
    initialState = MailContract.State()
) {
    init {
        getOnjungCount()
    }

    override fun reduceState(event: MailContract.Event) { }

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
                    postEffect(MailContract.Effect.ShowSnackBar("네트워크 에러가 발생하였습니다"))
                }
            }
        }
    }
}