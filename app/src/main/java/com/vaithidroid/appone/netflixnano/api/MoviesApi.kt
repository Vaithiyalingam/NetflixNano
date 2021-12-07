package com.vaithidroid.appone.netflixnano.api

import com.vaithidroid.appone.netflixnano.models.MoviesResponse
import com.vaithidroid.appone.netflixnano.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ) : Response<MoviesResponse>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ) : Response<MoviesResponse>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ) : Response<MoviesResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ) : Response<MoviesResponse>

}