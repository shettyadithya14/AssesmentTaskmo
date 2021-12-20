package com.assesment.taskmo.utils.di.component

import com.assesment.taskmo.PopMovApp
import com.assesment.taskmo.utils.database.DbModule
import com.assesment.taskmo.utils.di.module.AppModule
import com.assesment.taskmo.utils.di.module.RepoModule
import com.assesment.taskmo.utils.network.UrlModule
import com.assesment.taskmo.utils.network.NetworkModule
import com.assesment.taskmo.utils.di.builder.ActivityBuilder
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (DbModule::class),
    (NetworkModule::class), (UrlModule::class),(RepoModule::class), (ActivityBuilder::class)])

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: PopMovApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}