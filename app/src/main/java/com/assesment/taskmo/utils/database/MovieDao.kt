package com.assesment.taskmo.utils.database

import com.assesment.taskmo.movies.data.Movie
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT id FROM movies")
    fun fetchFavouriteMovies(): List<Int?>

    @Delete()
    fun removeMovie(movie: Movie)

}