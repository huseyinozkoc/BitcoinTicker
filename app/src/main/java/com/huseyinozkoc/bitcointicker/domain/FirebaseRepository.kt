package com.huseyinozkoc.bitcointicker.domain

interface FirebaseRepository {


    fun signInWithEmailAndPassword(email: String, password: String)


    fun signUpWithEmailAndPassword(email: String, password: String)

    fun signOut()


}