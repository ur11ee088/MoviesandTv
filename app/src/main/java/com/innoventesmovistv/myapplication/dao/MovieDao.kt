package com.innoventesmovistv.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.innoventesmovistv.myapplication.ui.model.MovieResult

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResult?)

    @Delete
    fun deleteMovie(movie: MovieResult?)

    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<MovieResult>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getSingleMovie(movieId:Int):LiveData<MovieResult>

}