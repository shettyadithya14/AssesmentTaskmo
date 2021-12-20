

package com.assesment.taskmo.movies.di

import com.assesment.taskmo.movies.presentation.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MoviesFragmentProvider {

    @ContributesAndroidInjector(modules =[(MoviesFragmentModule::class),(MovieSourceModule::class),])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}