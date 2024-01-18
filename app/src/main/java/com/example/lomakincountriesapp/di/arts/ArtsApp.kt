package com.example.lomakincountriesapp.di.arts

import android.app.Application

class ArtsApp : Application() {

    lateinit var artsComponent: ArtsComponent

    override fun onCreate() {
        super.onCreate()
        artsComponent = DaggerArtsComponent
            .builder()
            .build()
    }
}