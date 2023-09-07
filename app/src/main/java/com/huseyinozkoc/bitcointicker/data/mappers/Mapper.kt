package com.huseyinozkoc.bitcointicker.data.mappers

import com.huseyinozkoc.bitcointicker.common.Constants
import com.huseyinozkoc.bitcointicker.data.model.coindetail.CoinDetailResponse
import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListEntity
import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListResponse
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketEntity
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketResponse
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.model.CoinList
import com.huseyinozkoc.bitcointicker.domain.model.CoinMarkets
import com.huseyinozkoc.bitcointicker.domain.model.Favorites


fun CoinDetailResponse.toCoinDetailUI() = CoinDetail(
    name = name ?: Constants.NA,
    coinId = id,
    hashingAlgorithm = hashingAlgorithm ?: Constants.NA,
    description = description?.en ?: Constants.NA,
    image = image?.large,
    currentPrice = marketData?.currentPrice?.usd,
    priceChangePercentage24h = marketData?.priceChangePercentage24h
)

fun List<CoinListEntity>.toCoinListUI() = map {
    CoinList(
        id = it.id,
        coinId = it.coinId,
        name = it.name
    )
}

fun List<CoinListResponse>.toCoinListEntity() = map {
    CoinListEntity(
        coinId = it.id,
        name = it.name
    )
}

fun List<CoinMarketEntity>.toCoinMarketsUI() = map {
    CoinMarkets(
        id = it.id,
        coinId = it.coinId,
        currentPrice = it.currentPrice,
        image = it.image,
        name = it.name,
        priceChangePercentage24h = it.priceChangePercentage24h
    )
}

fun List<CoinMarketResponse>.toCoinMarketsEntity() = map {
    CoinMarketEntity(
        coinId = it.id,
        ath = it.ath,
        athChangePercentage = it.athChangePercentage,
        athDate = it.athDate,
        atl = it.atl,
        atlChangePercentage = it.atlChangePercentage,
        atlDate = it.atlDate,
        circulatingSupply = it.circulatingSupply,
        currentPrice = it.currentPrice,
        fullyDilutedValuation = it.fullyDilutedValuation,
        high24h = it.high24h,
        image = it.image,
        lastUpdated = it.lastUpdated,
        low24h = it.low24h,
        marketCap = it.marketCap,
        marketCapChange24h = it.marketCapChange24h,
        marketCapChangePercentage24h = it.marketCapChangePercentage24h,
        marketCapRank = it.marketCapRank,
        maxSupply = it.maxSupply,
        name = it.name,
        priceChange24h = it.priceChange24h,
        priceChangePercentage24h = it.priceChangePercentage24h,
        symbol = it.symbol,
        totalSupply = it.totalSupply,
        totalVolume = it.totalVolume
    )
}

fun CoinDetail.toFavouriteUI() = Favorites(
    name = name ?: "",
    coinId = coinId ?: "",
    hashingAlgorithm = hashingAlgorithm ?: "",
    description = description ?: "",
    image = image ?: "",
    currentPrice = currentPrice ?: 0.0,
    priceChangePercentage24h = priceChangePercentage24h ?: 0.0
)

