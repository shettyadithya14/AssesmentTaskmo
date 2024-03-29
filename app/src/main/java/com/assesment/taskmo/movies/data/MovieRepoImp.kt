package com.assesment.taskmo.movies.data

import com.assesment.taskmo.BuildConfig
import com.assesment.taskmo.detail.data.TrailerApi
import com.assesment.taskmo.detail.data.TrailerResponse
import com.assesment.taskmo.utils.database.MovieDao
import com.assesment.taskmo.utils.network.NetworkRouter
import com.assesment.taskmo.utils.network.ResultType
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepoImp @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi) : MovieRepo {

    private var moviesResponse: MovieResponse? =null

    override suspend fun getPopularMovies(page: Int): ResultType<MovieResponse> {
        val response = NetworkRouter.invokeCall { movieApi.getMostPopular(BuildConfig.API_KEY, page) }
        if (response is ResultType.Success) {
            if (moviesResponse == null) moviesResponse = response.data
            else
        moviesResponse?.results.also {
            it?.addAll(response.data.results)
        }?.let {
            moviesResponse = response.data.copy()
            moviesResponse!!.results = it
        }
        }
        return response
    }

    override suspend fun getFilteredPopularMovies(filterText: String): MovieResponse? {
        val result = moviesResponse?.results?.filter { movie -> movie.title?.contains(filterText, true) == true }?.toList()
        result?.let {
            val response = moviesResponse?.copy()
            response?.results = ArrayList(it)
            return response
        }
        return null
    }

    override suspend fun fetchMovieTrailers(movieId: Int): ResultType<TrailerResponse> {
        return NetworkRouter.invokeCall { trailerApi.getMovieTrailer(movieId, BuildConfig.API_KEY) }
    }

    override fun isMovieLiked(id: Int): Boolean {
        return movieDao.fetchFavouriteMovies().contains(id)
    }

    override fun changeLikeState(movie: Movie, newLikeState: Boolean) {
        if (newLikeState) movieDao.insertMovie(movie)
        else movieDao.removeMovie(movie)
    }

}