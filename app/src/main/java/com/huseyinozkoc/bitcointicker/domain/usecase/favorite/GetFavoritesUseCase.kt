package com.huseyinozkoc.bitcointicker.domain.usecase.favorite

import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke() = firebaseRepository.getFavourites()

}