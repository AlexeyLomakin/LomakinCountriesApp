package com.example.lomakincountriesapp.di.arts

import android.content.Context
import androidx.room.Room
import com.example.data.room.arts.ArtsDao
import com.example.data.room.arts.ArtsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ArtsDatabaseModule {
    @Singleton
    @Provides
    fun provideArtsDatabase(@ApplicationContext context: Context): ArtsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArtsDatabase::class.java,
            "arts_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideArtsDao(artsDatabase: ArtsDatabase): ArtsDao {
        return artsDatabase.artsDatabaseDao
    }

}