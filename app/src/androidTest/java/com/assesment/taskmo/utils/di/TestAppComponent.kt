package com.assesment.taskmo.utils.di

import com.assesment.taskmo.PopMovApp
import com.assesment.taskmo.utils.di.builder.ActivityBuilder
import com.assesment.taskmo.utils.di.component.AppComponent
import com.assesment.taskmo.utils.di.module.AppModule
import com.assesment.taskmo.utils.network.NetworkModule
import com.assesment.taskmo.utils.di.module.RepoModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DaggerApplication
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MockDbModule::class,
    NetworkModule::class,
    RepoModule::class,
    ActivityBuilder::class,
    MockUrlModule::class
]
)
interface TestAppComponent : AppComponent {

    override fun inject(app: PopMovApp)

    override fun inject(instance: DaggerApplication)

    fun getMockWebServer(): MockWebServer

    @Component.Builder
    interface Builder {

        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

}