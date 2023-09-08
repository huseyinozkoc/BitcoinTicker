package com.huseyinozkoc.bitcointicker.di

import android.app.Application
import com.huseyinozkoc.bitcointicker.domain.utils.StringResourceProvider
import com.huseyinozkoc.bitcointicker.utils.StringResourceProviderImpl
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

}