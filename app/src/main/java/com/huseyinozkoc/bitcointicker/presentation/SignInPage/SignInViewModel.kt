package com.huseyinozkoc.bitcointicker.presentation.SignInPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val _signInFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signInFlow = _signInFlow.asSharedFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInUseCase.invoke(email, password).collect {
            _signInFlow.emit(it)
        }
    }


}