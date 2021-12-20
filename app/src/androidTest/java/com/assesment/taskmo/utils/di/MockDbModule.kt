package com.assesment.taskmo.utils.di

import com.assesment.taskmo.utils.commons.AppConstants
import com.assesment.taskmo.utils.database.MovieDao
import com.assesment.taskmo.utils.database.MovieDb
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockDbModule {
        @Provides
        @Singleton
        @Named(AppConstants.DB_NAME_KEY)
        internal fun provideMovieDb(context: Context): MovieDb {
            return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_MOCK_NAME).fallbackToDestructiveMigration()
                    .build()
        }

        @Provides
        @Singleton
        internal fun provideMovieDao(context: Context): MovieDao {
            return provideMovieDb(context).movieDao()
        }
}