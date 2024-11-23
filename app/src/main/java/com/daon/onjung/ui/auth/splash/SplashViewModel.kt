package com.daon.onjung.ui.auth.splash

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SplashContract.State, SplashContract.Event, SplashContract.Effect>(
    initialState = SplashContract.State()
) {
    init {
        viewModelScope.launch {
            checkNotificationPermission()
            delay(1500)
            postEffect(SplashContract.Effect.NavigateTo(
                if(dataStoreRepository.getAccessToken().isEmpty()) {
                    Routes.Auth.LOGIN
                } else Routes.Home.ROUTE,
                navOptions {
                    popUpTo(Routes.Auth.ROUTE) { inclusive = true }
                }
            ))
        }
    }

    override fun reduceState(event: SplashContract.Event) {

    }

    private fun checkNotificationPermission() {
        val isNotFirstLaunch = dataStoreRepository.getIsNotFirstLaunch()

        if (!isNotFirstLaunch) {
            postEffect(SplashContract.Effect.CheckNotificationPermission)
            dataStoreRepository.setIsNotFirstLaunch(true)
        }
    }

}