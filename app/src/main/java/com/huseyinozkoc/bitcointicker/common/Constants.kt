package com.huseyinozkoc.bitcointicker.common

object Constants {
    const val BASE_URL = "https://api.coingecko.com/api/v3/"
    const val COIN_LIST = "coins/list"
    const val COIN_MARKETS ="coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&page=1&sparkline=false"
    const val COIN_BY_ID ="coins/{id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false"
    const val FAVORITES_COLLECTION = "favorites"
    const val COIN_ID = "coinId"
    const val NA = "N/A"
    const val SYNC_DATA_WORK_NAME = "syncDataWorkName"
    const val SYNC_DATA = "syncData"
    const val TITLE = "Bitcoin Ticker"
    const val DESCRIPTION = "Favori coinlerinin fiyatlarında değişim oldu! Görmek ister misin?"
    const val CHANNEL_ID = "MyTask"
    const val CHANNEL_NAME = "MyTaskChannel"
}