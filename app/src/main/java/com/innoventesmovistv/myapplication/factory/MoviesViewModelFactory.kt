package com.innoventesmovistv.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.innoventesmovistv.myapplication.repositories.MoviesRepositories
import com.innoventesmovistv.myapplication.ui.home.MoviesViewModel


@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(
    private val repository: MoviesRepositories
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository) as T
    }
}
