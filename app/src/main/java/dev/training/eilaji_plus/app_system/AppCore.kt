package dev.training.eilaji_plus.app_system

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppCore : Application() {
    // instance of the app

    companion object {
        @JvmStatic
        @get:Synchronized
        @set:Synchronized
        @Volatile
        var instance: AppCore? = null
            private set


        /*late-init var instance: AppCore*/
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}