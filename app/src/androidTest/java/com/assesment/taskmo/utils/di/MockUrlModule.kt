package com.assesment.taskmo.utils.di

import com.assesment.taskmo.utils.commons.AppConstants.Companion.BASE_URL_KEY
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockUrlModule {

    @Provides
    @Singleton
    fun provideMockServer (): MockWebServer {

        var mockWebServer:MockWebServer? = null

        val thread = Thread(Runnable {
            mockWebServer = MockWebServer()
            mockWebServer?.start()
        })

        thread.start()
        thread.join()

        return mockWebServer ?: throw NullPointerException()
    }


    @Provides
    @Singleton
    @Named(BASE_URL_KEY)
    fun provideBaseUrl (mockWebServer:MockWebServer): String {

        var url = ""

        val t = Thread(Runnable {
            url = mockWebServer.url("/").toString()
        })
        t.start()
        t.join()

        return url
    }
}