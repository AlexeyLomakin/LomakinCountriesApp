package com.example.lomakincountriesapp.ui.viewmodels

import com.example.lomakincountriesapp.ui.ArtsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ArtsModule::class])
interface ArtsComponent {
    fun inject(artsFragment: ArtsFragment)
}