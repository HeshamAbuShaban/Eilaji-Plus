package dev.training.eilaji_plus.core

import android.app.Application
import android.content.pm.PackageManager
import dagger.hilt.android.HiltAndroidApp
import dev.training.eilaji_plus.BuildConfig


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


        // Get the API_KEY from BuildConfig and set it as a meta-data in the Application tag
        val apiKey: String = BuildConfig.API_KEY
        try {
            val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val metaData = appInfo.metaData
            metaData.putString("API_KEY", apiKey)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }
}