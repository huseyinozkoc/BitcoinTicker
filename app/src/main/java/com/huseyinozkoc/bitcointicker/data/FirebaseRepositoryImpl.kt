package com.huseyinozkoc.bitcointicker.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.huseyinozkoc.bitcointicker.domain.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseRepository {

    override fun signInWithEmailAndPassword(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signUpWithEmailAndPassword(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }
}