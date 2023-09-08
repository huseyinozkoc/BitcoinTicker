package com.huseyinozkoc.bitcointicker.domain.usecase.favorite

import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(coinDetail: CoinDetail) = firebaseRepository.addToFavourites(coinDetail)
}