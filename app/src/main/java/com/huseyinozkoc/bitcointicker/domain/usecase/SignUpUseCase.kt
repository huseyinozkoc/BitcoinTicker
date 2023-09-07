package com.huseyinozkoc.bitcointicker.domain.usecase

import com.huseyinozkoc.bitcointicker.domain.FirebaseRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    operator fun invoke(email: String, password: String) =
        firebaseRepository.signUpWithEmailAndPassword(email, password)


}