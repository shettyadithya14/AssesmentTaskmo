package com.assesment.taskmo.detail.di

import com.assesment.taskmo.detail.presentation.DetailFragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides


@Module
class DetailFragmentModule {

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }


}