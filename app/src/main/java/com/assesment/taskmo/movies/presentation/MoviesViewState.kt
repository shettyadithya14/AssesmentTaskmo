package com.assesment.taskmo.movies.presentation

import com.assesment.taskmo.movies.data.Movie
import androidx.paging.PagingData


sealed class MoviesViewState {
    class FetchingMoviesError(val errorMessage: String?) : MoviesViewState()
    class FetchingMoviesSuccess(val movies: PagingData<Movie>) : MoviesViewState()
}
