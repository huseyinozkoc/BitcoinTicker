package com.huseyinozkoc.bitcointicker.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.huseyinozkoc.bitcointicker.common.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {


    fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>


    fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

    fun getFirebaseUserUid(): Flow<String>

    fun isCurrentUserExist(): Flow<Boolean>

    fun getCurrentUser(): Flow<Resource<FirebaseUser>>



}