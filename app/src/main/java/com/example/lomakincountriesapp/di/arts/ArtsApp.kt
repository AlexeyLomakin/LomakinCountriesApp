package com.example.lomakincountriesapp.di.arts

import android.app.Application

class ArtsApp : Application() {

    private lateinit var artsComponent: ArtsComponent

    override fun onCreate() {
        super.onCreate()
        artsComponent = DaggerArtsComponent.builder()
            .artsModule(ArtsModule())
            .build()
    }
}