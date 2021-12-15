package com.vaithidroid.appone.netflixnano.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vaithidroid.appone.netflixnano.models.Result

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result : Result) : Long

    @Query("SELECT * FROM results")
    fun getAllMovies() : LiveData<List<Result>>

    @Delete
    suspend fun deleteArticle(result: Result)

}