package com.assesment.taskmo.utils.network

import com.assesment.taskmo.BuildConfig
import com.assesment.taskmo.utils.commons.AppConstants.Companion.BASE_URL_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UrlModule {
    @Provides
    @Singleton
    @Named(BASE_URL_KEY)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}