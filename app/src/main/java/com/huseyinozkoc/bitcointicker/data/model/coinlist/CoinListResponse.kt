package com.huseyinozkoc.bitcointicker.data.model.coinlist

data class CoinListResponse(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val platforms: Map<String, String>?
)