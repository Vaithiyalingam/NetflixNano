package com.vaithidroid.appone.netflixnano.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaithidroid.appone.netflixnano.repository.MoviesRepository

class MoviesViewModelProviderFactory(
    val app : Application,
    val moviesRepository: MoviesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(app, moviesRepository) as T
    }

}