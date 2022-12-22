package uz.gita.mobile_banking.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.ldralighieri.corbind.BuildConfig
import timber.log.Timber

// Created by Jamshid Isoqov on 12/22/2022
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.asTree())
        }
        instance = this
    }

}