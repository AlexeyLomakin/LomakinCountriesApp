package com.example.lomakincountriesapp.di.arts

import com.example.lomakincountriesapp.presentation.arts.ArtsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ArtsModule::class])
interface ArtsComponent {
    fun inject(artsFragment: ArtsFragment)
}