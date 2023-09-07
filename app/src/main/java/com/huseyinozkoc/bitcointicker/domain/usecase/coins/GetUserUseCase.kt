package com.huseyinozkoc.bitcointicker.domain.usecase.coins

import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    operator fun invoke() = firebaseRepository.getCurrentUser()
}