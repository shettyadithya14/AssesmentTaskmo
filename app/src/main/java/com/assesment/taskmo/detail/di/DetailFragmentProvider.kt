

package com.assesment.taskmo.detail.di

import com.assesment.taskmo.detail.presentation.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DetailFragmentProvider {

    @ContributesAndroidInjector(modules =[(DetailFragmentModule::class)])
    internal abstract fun provideDetailFragmentFactory(): DetailFragment

}