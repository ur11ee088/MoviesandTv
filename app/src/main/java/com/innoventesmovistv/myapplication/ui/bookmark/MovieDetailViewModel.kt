package com.innoventesmovistv.myapplication.ui.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.innoventesmovistv.myapplication.ui.model.MovieResult


class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieDetailRepository by lazy {
        MovieDetailRepository(
            application.applicationContext
        )
    }



    fun insertMovie(movie: MovieResult?) = repository.insertMovie(movie)
    fun deleteMovie(movie: MovieResult?) = repository.deleteMovie(movie)
    fun getSingleMovie(movieId:Int?) : LiveData<MovieResult> = repository.getSingleMovie(movieId)
    fun getAllMovies(): LiveData<List<MovieResult>> = repository.getAllMovies()



    override fun onCleared() {
        super.onCleared()
    }


}