package com.huseyinozkoc.bitcointicker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huseyinozkoc.bitcointicker.data.model.coinlist.CoinListEntity
import com.huseyinozkoc.bitcointicker.data.model.coinmarket.CoinMarketEntity

@Database(
    entities = [CoinListEntity::class, CoinMarketEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsRoomDB : RoomDatabase() {
    abstract fun coinsDAO(): CoinsDAO
}