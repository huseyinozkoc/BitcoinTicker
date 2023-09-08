package com.huseyinozkoc.bitcointicker.di

import android.app.Application
import com.huseyinozkoc.bitcointicker.domain.utils.StringResourceProvider
import com.huseyinozkoc.bitcointicker.domain.utils.WorkerInterface
import com.huseyinozkoc.bitcointicker.utils.StringResourceProviderImpl
import com.huseyinozkoc.bitcointicker.utils.WorkerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {


    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourceProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideWorkerProvider(application: Application): WorkerInterface {
        return WorkerImpl(application)
    }

}