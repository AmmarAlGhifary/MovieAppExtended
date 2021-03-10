package com.blogspot.yourfavoritekaisar.movieapp2.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blogspot.yourfavoritekaisar.movieapp2.data.dao.MovieDao
import com.blogspot.yourfavoritekaisar.movieapp2.data.dao.TvShowDao
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.entity.MovieEntity
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}