
package com.assesment.taskmo.utils.di.module

import com.assesment.taskmo.detail.data.TrailerApi
import com.assesment.taskmo.movies.data.MovieApi
import com.assesment.taskmo.movies.data.MovieRepo
import com.assesment.taskmo.movies.data.MovieRepoImp
import com.assesment.taskmo.utils.database.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieDao: MovieDao,
                                        movieApi: MovieApi,
                                        trailerApi: TrailerApi): MovieRepo {
        return MovieRepoImp(movieDao, movieApi, trailerApi)
    }

}