package com.huseyinozkoc.bitcointicker.domain.repository

import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.model.CoinList
import com.huseyinozkoc.bitcointicker.domain.model.CoinMarkets
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface CoinRepository {
    fun coinsMarkets(): Flow<Resource<List<CoinMarkets>>>

    fun coinList(): Flow<Resource<List<CoinList>>>

    fun searchCoin(searchQuery: String): Flow<Resource<List<CoinList>>>

    fun coinById(coinId: String): Flow<Resource<CoinDetail>>

    fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>>
}