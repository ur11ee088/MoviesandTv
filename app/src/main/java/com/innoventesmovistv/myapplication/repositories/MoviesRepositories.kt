package com.innoventesmovistv.myapplication.repositories

import com.innoventesmovistv.myapplication.network.MoviesApis
import com.innoventesmovistv.myapplication.network.SafeApiRequest


class MoviesRepositories(private val api: MoviesApis) : SafeApiRequest() {
    suspend fun searchMovies(search: String, page: Int, key: String) =
        apiRequest { api.searchMovies(search, page, key) }
}