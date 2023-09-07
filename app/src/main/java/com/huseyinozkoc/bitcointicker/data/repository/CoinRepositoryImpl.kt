package com.huseyinozkoc.bitcointicker.data.repository

import androidx.room.withTransaction
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.data.mappers.*
import com.huseyinozkoc.bitcointicker.data.source.local.CoinsRoomDB
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.model.CoinList
import com.huseyinozkoc.bitcointicker.domain.model.CoinMarkets
import com.huseyinozkoc.bitcointicker.domain.repository.CoinRepository
import com.huseyinozkoc.bitcointicker.domain.source.LocalDataSource
import com.huseyinozkoc.bitcointicker.domain.source.RemoteDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Named
import kotlin.time.Duration

class CoinRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coinsRoomDB: CoinsRoomDB,
    @Named("Default") private val coroutineContextDefault: CoroutineDispatcher
) : CoinRepository {

    private var job: Job? = null


    override fun coinsMarkets(): Flow<Resource<List<CoinMarkets>>> = flow {
        emit(Resource.Loading)
        val cache = localDataSource.getCoinMarkets()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toCoinMarketsUI()))
        }
        val response = try {
            remoteDataSource.getCoinMarkets()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            null
        }
        response?.let { data ->
            coinsRoomDB.withTransaction {
                localDataSource.deleteCoinMarkets()
                localDataSource.insertCoinMarketsList(data.toCoinMarketsEntity())
            }
            emit(Resource.Success(localDataSource.getCoinMarkets().toCoinMarketsUI()))
        }
    }

    override fun coinList(): Flow<Resource<List<CoinList>>> = flow {
        emit(Resource.Loading)
        val cache = localDataSource.getCoinList()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toCoinListUI()))
        }
        val response = try {
            remoteDataSource.getCoinList()
        } catch (throwable: Throwable) {
            Resource.Error(throwable)
            null
        }
        response?.let { data ->
            localDataSource.deleteCoinList()
            localDataSource.insertCoinList(data.toCoinListEntity())
        }
        emit(Resource.Success(localDataSource.getCoinList().toCoinListUI()))
    }

    override fun searchCoin(searchQuery: String): Flow<Resource<List<CoinList>>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(localDataSource.searchCoin(searchQuery).toCoinListUI()))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun coinById(coinId: String) = flow {
        emit(Resource.Loading)
        emit(Resource.Success(remoteDataSource.getCoinById(coinId).toCoinDetailUI()))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>> =
        channelFlow {
            job?.cancel()
            job = CoroutineScope(coroutineContextDefault).launch {
                while (true) {
                    send(Resource.Loading)
                    val data = remoteDataSource.getCoinById(coinId).toCoinDetailUI()
                    data.currentPrice?.let {
                        send(Resource.Success(it))
                    }
                    delay(period)
                }
            }
            awaitClose {
                channel.close()
                job?.cancel()
            }
        }.catch {
            emit(Resource.Error(it))
        }
}


