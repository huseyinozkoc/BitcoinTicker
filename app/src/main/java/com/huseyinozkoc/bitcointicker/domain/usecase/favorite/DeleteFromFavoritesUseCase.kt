package com.huseyinozkoc.bitcointicker.domain.usecase.favorite

import com.huseyinozkoc.bitcointicker.domain.model.Favorites
import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(favouritesUI: Favorites) =
        firebaseRepository.deleteFromFavourites(favouritesUI)
}