package com.example.lomakincountriesapp.ui.viewmodels

import android.app.Application

class ArtsApp : Application() {

    lateinit var appComponent: ArtsComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerArtsComponent
            .builder()
            .artsModule(ArtsModule())
            .build()
    }
}