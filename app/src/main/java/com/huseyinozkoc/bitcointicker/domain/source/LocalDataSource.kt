package com.huseyinozkoc.bitcointicker.domain.source

import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListEntity
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketEntity


interface LocalDataSource {
    suspend fun insertCoinList(items: List<CoinListEntity>)

    suspend fun insertCoinMarketsList(items: List<CoinMarketEntity>)

    suspend fun getCoinList(): List<CoinListEntity>

    suspend fun getCoinMarkets(): List<CoinMarketEntity>

    suspend fun searchCoin(searchQuery: String): List<CoinListEntity>

    suspend fun deleteCoinList()

    suspend fun deleteCoinMarkets()
}