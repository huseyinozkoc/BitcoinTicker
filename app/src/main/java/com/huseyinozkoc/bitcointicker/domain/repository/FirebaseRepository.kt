package com.huseyinozkoc.bitcointicker.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.model.Favorites
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {


    fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>


    fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

    fun getFirebaseUserUid(): Flow<String>

    fun isCurrentUserExist(): Flow<Boolean>

    fun getCurrentUser(): Flow<Resource<FirebaseUser>>

    fun addToFavourites(coinDetailUI: CoinDetail): Flow<Resource<Task<Void>>>

    fun getFavourites(): Flow<Resource<List<Favorites>>>

    fun deleteFromFavourites(favouritesUI: Favorites): Flow<Resource<Task<Void>>>


}