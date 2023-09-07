package com.huseyinozkoc.bitcointicker.di

import com.huseyinozkoc.bitcointicker.data.repository.CoinRepositoryImpl
import com.huseyinozkoc.bitcointicker.data.source.local.CoinsRoomDB
import com.huseyinozkoc.bitcointicker.domain.repository.CoinRepository
import com.huseyinozkoc.bitcointicker.domain.source.LocalDataSource
import com.huseyinozkoc.bitcointicker.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        coinsRoomDB: CoinsRoomDB,
        @Named("Default") coroutineContextDefault: CoroutineDispatcher
    ): CoinRepository =
        CoinRepositoryImpl(remoteDataSource, localDataSource, coinsRoomDB, coroutineContextDefault)

    @Provides
    @Singleton
    @Named("IO")
    fun provideCoContextIO(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    @Named("Default")
    fun provideCoContextDefault(): CoroutineDispatcher = Dispatchers.Default

}