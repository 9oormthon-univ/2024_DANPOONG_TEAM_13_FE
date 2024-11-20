package com.daon.onjung.ui.home

import androidx.lifecycle.viewModelScope
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val onjungRepository: OnjungRepository
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    override fun reduceState(event: HomeContract.Event) {

    }

    fun getOnjungSummary() = viewModelScope.launch {
        onjungRepository.getOnjungSummary()
            .onStart { updateState(currentState.copy(isLoading = true)) }
            .collect {
                updateState(currentState.copy(isLoading = false))
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.data?.let {
                            updateState(currentState.copy(onjungSummary = it))
                        }
                    }
                    is ApiResult.ApiError ->{
                        postEffect(HomeContract.Effect.ShowSnackBar(it.message))
                    }
                    is ApiResult.NetworkError -> {
                        postEffect(HomeContract.Effect.ShowSnackBar("네트워크 에러가 발생하였습니다"))
                    }
                }
            }
        }
}