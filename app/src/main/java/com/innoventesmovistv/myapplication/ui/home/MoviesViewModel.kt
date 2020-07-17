package com.innoventesmovistv.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innoventesmovistv.myapplication.exceptions.ApiException
import com.innoventesmovistv.myapplication.exceptions.NoInternetException
import com.innoventesmovistv.myapplication.repositories.MoviesRepositories
import com.innoventesmovistv.myapplication.ui.base.BaseViewModel
import com.innoventesmovistv.myapplication.ui.base.ErrorWrapper
import com.innoventesmovistv.myapplication.ui.model.MovieMainResponse

class MoviesViewModel(private val repository: MoviesRepositories) : BaseViewModel() {
    private val _searchMovieResponse = MutableLiveData<MovieMainResponse>()
    val searchMoviesResponse: LiveData<MovieMainResponse>
        get() = _searchMovieResponse

    suspend fun getMovies(search: String, page: Int, key: String) {
        try {
            _searchMovieResponse.value =
                repository.searchMovies(search, page, key)
        } catch (e: ApiException) {
            mError.value = ErrorWrapper(e.errorCode, e._message)
        } catch (e: NoInternetException) {
            mError.value = ErrorWrapper(e.errorCode, e._message)
        }
    }


}