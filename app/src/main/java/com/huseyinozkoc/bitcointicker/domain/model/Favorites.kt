package com.huseyinozkoc.bitcointicker.domain.model

data class Favorites(
    val name: String? = "",
    val coinId: String? = "",
    val hashingAlgorithm: String? = "",
    val description: String? = "",
    val image: String? = "",
    val currentPrice: Double? = 0.0,
    val priceChangePercentage24h: Double? = 0.0
)
