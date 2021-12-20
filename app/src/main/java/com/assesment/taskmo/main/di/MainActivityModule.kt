package com.assesment.taskmo.main.di

import com.assesment.taskmo.utils.commons.ViewModelProviderFactory
import com.assesment.taskmo.movies.presentation.MoviesViewModel
import com.assesment.taskmo.movies.data.MovieRepoImp
import com.assesment.taskmo.movies.domain.MovieSourceFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMoviesViewModel(movieRepoImp: MovieRepoImp,sourceFactory:MovieSourceFactory): MoviesViewModel {
        return MoviesViewModel(movieRepoImp,sourceFactory)
    }

    @Provides
    internal fun provideMoviesViewModelFactory(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}