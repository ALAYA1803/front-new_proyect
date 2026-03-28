package com.presto.prezto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PrestoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}