package com.huseyinozkoc.bitcointicker.di

import com.huseyinozkoc.bitcointicker.data.source.local.CoinsDAO
import com.huseyinozkoc.bitcointicker.data.source.local.RoomDataSourceImpl
import com.huseyinozkoc.bitcointicker.data.source.remote.CoinService
import com.huseyinozkoc.bitcointicker.data.source.remote.RemoteDataSourceImpl
import com.huseyinozkoc.bitcointicker.domain.source.LocalDataSource
import com.huseyinozkoc.bitcointicker.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        coinService: CoinService,
    ): RemoteDataSource =
        RemoteDataSourceImpl(coinService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        coinsDAO: CoinsDAO
    ): LocalDataSource = RoomDataSourceImpl(coinsDAO)
}