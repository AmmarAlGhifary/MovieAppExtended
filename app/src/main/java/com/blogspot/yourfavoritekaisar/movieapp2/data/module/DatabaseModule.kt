package com.blogspot.yourfavoritekaisar.movieapp2.data.module

import android.app.Application
import androidx.room.Room
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "mymovies.db"
        ).allowMainThreadQueries().build()
    }
}