package com.example.lomakincountriesapp.di.arts

import android.app.Application
import com.example.lomakincountriesapp.di.DaggerArtsComponent

class ArtsApp : Application() {

    private lateinit var artsComponent: ArtsComponent

    override fun onCreate() {
        super.onCreate()
        artsComponent = DaggerArtsComponent.builder()
            .artsModule(ArtsModule())
            .build()
    }
}