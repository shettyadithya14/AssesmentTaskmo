package com.assesment.taskmo.utils.database

import com.assesment.taskmo.movies.data.Movie
import androidx.room.*
import javax.inject.Singleton


@Singleton
@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}


