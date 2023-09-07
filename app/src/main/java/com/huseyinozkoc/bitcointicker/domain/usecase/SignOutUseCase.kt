package com.huseyinozkoc.bitcointicker.domain.usecase

import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    operator fun invoke() = firebaseRepository.signOut()

}