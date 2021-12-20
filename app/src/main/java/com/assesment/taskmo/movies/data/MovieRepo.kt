package com.assesment.taskmo.movies.data

import com.assesment.taskmo.detail.data.TrailerResponse
import com.assesment.taskmo.utils.network.ResultType

interface MovieRepo {
    suspend fun getPopularMovies(page: Int): ResultType<MovieResponse>
    suspend fun getFilteredPopularMovies(filterText: String): MovieResponse?
    suspend fun fetchMovieTrailers(movieId: Int): ResultType<TrailerResponse>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(movie: Movie, newLikeState: Boolean)
}