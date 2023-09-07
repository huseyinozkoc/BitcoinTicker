package com.huseyinozkoc.bitcointicker.presentation.SignUpPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signUpFlow = _signUpFlow.asSharedFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpUseCase.invoke(email, password).collect {
            _signUpFlow.emit(it)
        }
    }


}