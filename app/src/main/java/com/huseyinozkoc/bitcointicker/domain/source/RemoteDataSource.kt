package com.huseyinozkoc.bitcointicker.domain.source

import com.huseyinozkoc.bitcointicker.data.model.coindetail.CoinDetailResponse
import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListResponse
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketResponse

interface RemoteDataSource {
    suspend fun getCoinList(): List<CoinListResponse>

    suspend fun getCoinMarkets(): List<CoinMarketResponse>

    suspend fun getCoinById(coinId: String): CoinDetailResponse
}