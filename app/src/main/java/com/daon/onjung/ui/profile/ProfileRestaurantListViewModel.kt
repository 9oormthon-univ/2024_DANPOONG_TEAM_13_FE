package com.daon.onjung.ui.profile

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Constants
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.StoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileRestaurantListViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : BaseViewModel<ProfileRestaurantListContract.State, ProfileRestaurantListContract.Event, ProfileRestaurantListContract.Effect>(
    initialState = ProfileRestaurantListContract.State()
) {
    init {
        getRestaurantList()
    }

    override fun reduceState(event: ProfileRestaurantListContract.Event) {
        when (event) {
            is ProfileRestaurantListContract.Event.RestaurantClicked -> {
                postEffect(ProfileRestaurantListContract.Effect.NavigateTo("${Routes.Home.SHOP_DETAIL}?shopId=${event.id}"))
            }
        }
    }

    private fun getRestaurantList() = viewModelScope.launch {
        storeRepository.getSharedRestaurantList().onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    it.data?.data?.let { result ->
                        updateState(currentState.copy(restaurantList = result.storeList))
                    }
                }
                is ApiResult.ApiError -> {
                    postEffect(ProfileRestaurantListContract.Effect.ShowSnackBar(it.message))
                }
                is ApiResult.NetworkError -> {
                    postEffect(ProfileRestaurantListContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                }
            }
        }
    }
}