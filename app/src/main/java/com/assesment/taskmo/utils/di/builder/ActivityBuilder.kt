

package com.assesment.taskmo.utils.di.builder

import com.assesment.taskmo.detail.di.DetailFragmentProvider
import com.assesment.taskmo.main.di.MainActivityModule
import com.assesment.taskmo.movies.di.MoviesFragmentProvider
import com.assesment.taskmo.main.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MoviesFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}