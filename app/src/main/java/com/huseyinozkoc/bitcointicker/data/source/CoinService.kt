package com.huseyinozkoc.bitcointicker.data.source

import com.huseyinozkoc.bitcointicker.common.Constants.COIN_BY_ID
import com.huseyinozkoc.bitcointicker.common.Constants.COIN_LIST
import com.huseyinozkoc.bitcointicker.common.Constants.COIN_MARKETS
import com.huseyinozkoc.bitcointicker.data.model.coindetail.CoinDetailResponse
import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListResponse
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET(COIN_LIST)
    suspend fun getCoinList(): List<CoinListResponse>

    @GET(COIN_MARKETS)
    suspend fun getCoinMarkets(): List<CoinMarketResponse>

    @GET(COIN_BY_ID)
    suspend fun getCoinById(@Path("id") coinId: String): CoinDetailResponse
}