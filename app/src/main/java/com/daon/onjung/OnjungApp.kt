package com.daon.onjung

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.util.Utility

class OnjungApp : Application() {

    override fun onCreate() {
        super.onCreate()

        var keyHash = Utility.getKeyHash(this)
        Log.d("OnjungApp", "keyHash: $keyHash")
    }
}