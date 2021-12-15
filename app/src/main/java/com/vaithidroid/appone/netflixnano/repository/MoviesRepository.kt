package com.vaithidroid.appone.netflixnano.repository

import com.vaithidroid.appone.netflixnano.api.RetrofitInstance
import com.vaithidroid.appone.netflixnano.db.MoviesDatabase

class MoviesRepository(
    val db : MoviesDatabase
) {

    suspend fun getNowPlayingMovies(pageNumber : Int) =
        RetrofitInstance.api.getNowPlayingMovies(pageNumber)

    suspend fun getPopularMovies(pageNumber: Int) =
        RetrofitInstance.api.getPopularMovies(pageNumber)

    suspend fun getTopRatedMovies(pageNumber: Int) =
        RetrofitInstance.api.getTopRatedMovies(pageNumber)

    suspend fun getUpcomingMovies(pageNumber: Int) =
        RetrofitInstance.api.getUpcomingMovies(pageNumber)

}