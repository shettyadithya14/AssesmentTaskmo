

package com.assesment.taskmo.movies.di

import com.assesment.taskmo.utils.commons.GridSpacingItemDecoration
import com.assesment.taskmo.movies.presentation.MovieAdapter
import com.assesment.taskmo.movies.presentation.MoviesFragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.Module
import dagger.Provides


@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager(fragment.requireContext(), 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter()
    }

}