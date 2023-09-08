package com.huseyinozkoc.bitcointicker.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.huseyinozkoc.bitcointicker.common.Constants.FAVORITES_COLLECTION
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.data.mappers.toFavouriteUI
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.model.Favorites
import com.huseyinozkoc.bitcointicker.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseRepository {

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await()))
    }.catch { emit(Resource.Error(it)) }

    override fun signUpWithEmailAndPassword(
        email: String, password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(firebaseAuth.createUserWithEmailAndPassword(email, password).await()))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun signOut() = firebaseAuth.signOut()


    override fun getFirebaseUserUid(): Flow<String> = flow {
        firebaseAuth.currentUser?.uid?.let {
            emit(it)
        }
    }

    override fun isCurrentUserExist(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }

    override fun getCurrentUser(): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading)

        firebaseAuth.currentUser?.let {
            emit(Resource.Success(it))
        }
    }.catch {
        emit(Resource.Error(it))
    }

    override fun addToFavourites(coinDetailUI: CoinDetail): Flow<Resource<Task<Void>>> = flow {

        emit(Resource.Loading)

        getFirebaseUserUid().collect {
            val favRef =
                firebaseFirestore.collection(FAVORITES_COLLECTION).document(it).collection("coins")
                    .document(coinDetailUI.toFavouriteUI().name.orEmpty())
                    .set(coinDetailUI.toFavouriteUI())

            favRef.await()

            emit(Resource.Success(favRef))
        }

    }.catch {
        emit(Resource.Error(it))
    }

    override fun getFavourites(): Flow<Resource<List<Favorites>>> = flow {

        emit(Resource.Loading)

        getFirebaseUserUid().collect {

            val snapshot =
                firebaseFirestore.collection(FAVORITES_COLLECTION).document(it).collection("coins")
                    .get().await()

            val data = snapshot.toObjects(Favorites::class.java)

            emit(Resource.Success(data))
        }
    }.catch {
        emit(Resource.Error(it))
    }

    override fun deleteFromFavourites(favouritesUI: Favorites): Flow<Resource<Task<Void>>> =
        flow {

            emit(Resource.Loading)

            getFirebaseUserUid().collect {

                val favRef = firebaseFirestore.collection(FAVORITES_COLLECTION).document(it)
                    .collection("coins").document(favouritesUI.name.orEmpty()).delete()

                favRef.await()

                emit(Resource.Success(favRef))
            }

        }.catch {
            emit(Resource.Error(it))
        }


}