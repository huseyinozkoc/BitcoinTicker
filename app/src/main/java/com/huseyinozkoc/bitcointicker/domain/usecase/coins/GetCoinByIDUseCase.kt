package com.huseyinozkoc.bitcointicker.domain.usecase.coins

import com.huseyinozkoc.bitcointicker.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinByIDUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String) = coinRepository.coinById(coinId)

}