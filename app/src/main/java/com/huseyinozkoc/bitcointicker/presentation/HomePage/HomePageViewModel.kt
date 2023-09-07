package com.huseyinozkoc.bitcointicker.presentation.HomePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.model.CoinMarkets
import com.huseyinozkoc.bitcointicker.domain.usecase.SignOutUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.GetCoinMarketUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getCoinMarketsUseCase: GetCoinMarketUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _coinMarketsFlow = MutableStateFlow<Resource<List<CoinMarkets>>>(Resource.Loading)
    val coinMarketsFlow = _coinMarketsFlow.asStateFlow()


    init {
        coinMarkets()
    }


     fun coinMarkets() = viewModelScope.launch {
        getCoinMarketsUseCase.invoke().collect { result ->
            _coinMarketsFlow.emit(result)
        }
    }

    fun signOut() = signOutUseCase.invoke()

    val currentUser = getUserUseCase.invoke()


}