package com.daon.onjung.ui.community

import com.daon.onjung.util.BaseViewModel
import javax.inject.Inject

class CommunityViewModel @Inject constructor(

) : BaseViewModel<CommunityContract.State, CommunityContract.Event, CommunityContract.Effect>(
    initialState = CommunityContract.State()
) {
    override fun reduceState(event: CommunityContract.Event) {
        when (event) {
            is CommunityContract.Event.SelectPost -> {

            }
        }
    }


}