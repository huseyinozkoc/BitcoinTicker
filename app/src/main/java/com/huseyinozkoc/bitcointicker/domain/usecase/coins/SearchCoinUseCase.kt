package com.huseyinozkoc.bitcointicker.domain.usecase.coins

import com.huseyinozkoc.bitcointicker.domain.repository.CoinRepository
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(searchQuery: String) = coinRepository.searchCoin(searchQuery)

}