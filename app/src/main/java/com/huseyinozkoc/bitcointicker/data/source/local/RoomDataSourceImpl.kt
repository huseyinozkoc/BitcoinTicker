package com.huseyinozkoc.bitcointicker.data.source.local

import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListEntity
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketEntity
import com.huseyinozkoc.bitcointicker.domain.source.LocalDataSource
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val coinsDAO: CoinsDAO
) : LocalDataSource {

    override suspend fun insertCoinList(items: List<CoinListEntity>) =
        coinsDAO.insertCoinList(items)

    override suspend fun insertCoinMarketsList(items: List<CoinMarketEntity>) =
        coinsDAO.insertCoinMarkets(items)

    override suspend fun getCoinList() = coinsDAO.getCoinList()

    override suspend fun getCoinMarkets() = coinsDAO.getCoinMarkets()

    override suspend fun searchCoin(searchQuery: String) = coinsDAO.searchCoin(searchQuery)

    override suspend fun deleteCoinList() = coinsDAO.deleteCoinList()

    override suspend fun deleteCoinMarkets() = coinsDAO.deleteCoinMarkets()
}