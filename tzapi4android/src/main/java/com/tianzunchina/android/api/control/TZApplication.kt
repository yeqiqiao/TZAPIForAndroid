package com.tianzunchina.android.api.control

import android.app.Application

/**
 * Created by admin on 2016/3/4 0004.
 */
class TZApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: TZApplication? = null
            private set
    }
}