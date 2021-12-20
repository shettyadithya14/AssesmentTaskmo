package com.assesment.taskmo.movies.di

import com.assesment.taskmo.movies.data.MovieFilterSource
import com.assesment.taskmo.movies.data.MoviePagingSource
import com.assesment.taskmo.movies.data.MovieRepoImp
import com.assesment.taskmo.movies.domain.MovieSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieSourceModule {
    @Provides
    internal fun provideMoviesSource(): MovieSourceFactory {
        return MovieSourceFactory()
    }

    @Provides
    @Singleton
    internal fun provideMoviesPagingSource(movieRepoImp: MovieRepoImp): MoviePagingSource {
        return MoviePagingSource(movieRepoImp)
    }

    @Provides
    @Singleton
    internal fun provideMoviesFilterSource(movieRepoImp: MovieRepoImp): MovieFilterSource {
        return MovieFilterSource(movieRepoImp)
    }
}