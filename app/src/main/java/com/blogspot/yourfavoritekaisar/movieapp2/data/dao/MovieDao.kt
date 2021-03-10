package com.blogspot.yourfavoritekaisar.movieapp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_movies")
    fun getAll(): List<MovieEntity>

    @Insert
    fun insert(movie: MovieEntity)

    @Query("SELECT * FROM tbl_movies WHERE id = :id LIMIT 1")
    fun findById(id: Long): MovieEntity?

    @Query("DELETE FROM tbl_movies WHERE id = :id")
    fun delete(id: Long)
}