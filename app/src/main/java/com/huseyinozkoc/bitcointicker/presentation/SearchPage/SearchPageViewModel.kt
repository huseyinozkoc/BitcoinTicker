package com.huseyinozkoc.bitcointicker.presentation.SearchPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.domain.model.CoinList
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.GetCoinListUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.SearchCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    getCoinListUseCase: GetCoinListUseCase,
    private val searchCoinUseCase: SearchCoinUseCase,
) : ViewModel() {

    private val _coinListFlow = MutableStateFlow<Resource<List<CoinList>>>(Resource.Loading)
    val coinListFlow = _coinListFlow.asStateFlow()

    val coinList = getCoinListUseCase.invoke()

    fun searchCoin(searchQuery: String) = viewModelScope.launch {
        searchCoinUseCase.invoke(searchQuery).collect {
            _coinListFlow.emit(it)
        }
    }
}