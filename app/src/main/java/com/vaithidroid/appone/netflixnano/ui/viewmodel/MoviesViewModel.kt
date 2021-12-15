package com.vaithidroid.appone.netflixnano.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vaithidroid.appone.netflixnano.models.MoviesResponse
import com.vaithidroid.appone.netflixnano.repository.MoviesRepository
import com.vaithidroid.appone.netflixnano.util.Resource
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(
    app : Application,
    val moviesRepository: MoviesRepository
): AndroidViewModel(app) {

    val nowPlayingMovies : MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var nowPlayingPage = 1
    var moviesResponse: MoviesResponse? = null

    val popularMovies : MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var popularPage = 1

    val topRatedMovies : MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var topRatedPage = 1

    val upcomingMovies : MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var upcomingPage = 1

    init {
        getNowPlayingMovies()
    }

    init {
        getPopularMovies()
    }

    init {
        getTopRatedMovies()
    }

    init {
        getUpcomingMovies()
    }


    fun getNowPlayingMovies() = viewModelScope.launch {
        nowPlayingMovies.postValue(Resource.Loading())
        val response = moviesRepository.getNowPlayingMovies(nowPlayingPage)
        nowPlayingMovies.postValue(handlingNowPlayingMovies(response))
    }


    fun getPopularMovies() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = moviesRepository.getPopularMovies(popularPage)
        popularMovies.postValue(handlingPopularMovies(response))
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response = moviesRepository.getTopRatedMovies(topRatedPage)
        topRatedMovies.postValue(handlingTopRatedMovies(response))
    }

    fun getUpcomingMovies() = viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response = moviesRepository.getUpcomingMovies(upcomingPage)
        upcomingMovies.postValue(handlingUpcomingMovies(response))
    }


    private fun handlingNowPlayingMovies(response: Response<MoviesResponse>) : Resource<MoviesResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
            return Resource.Error(response.message())
    }

    private fun handlingPopularMovies(response: Response<MoviesResponse>) : Resource<MoviesResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlingTopRatedMovies(response: Response<MoviesResponse>) : Resource<MoviesResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlingUpcomingMovies(response: Response<MoviesResponse>) : Resource<MoviesResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}