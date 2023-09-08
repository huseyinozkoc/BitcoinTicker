package com.huseyinozkoc.bitcointicker.presentation.DetailPage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.Constants.COIN_ID
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.common.UIScreen
import com.huseyinozkoc.bitcointicker.domain.model.CoinDetail
import com.huseyinozkoc.bitcointicker.domain.usecase.favorite.AddToFavouritesUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.GetCoinByIDUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.coins.GetCurrentPriceByIdUseCase
import com.huseyinozkoc.bitcointicker.domain.utils.StringResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIDUseCase,
    private val currentPriceByIdUseCase: GetCurrentPriceByIdUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private var coinDetailUI: CoinDetail? = null

    private val _coinDetailFlow = MutableStateFlow<Resource<CoinDetail>>(Resource.Loading)
    val coinDetailFlow = _coinDetailFlow.asStateFlow()

    private val _currentPriceFlow = MutableStateFlow(0.0)
    val currentPriceFlow = _currentPriceFlow.asStateFlow()

    private val _addToFavourite = MutableSharedFlow<UIScreen>()
    val addToFavourite = _addToFavourite.asSharedFlow()


    init {
        savedStateHandle.get<String>(COIN_ID)?.let {
            coinById(it)
        }
    }


    private fun coinById(coinId: String) = viewModelScope.launch {
        getCoinByIdUseCase.invoke(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    coinDetailUI = result.data
                    _coinDetailFlow.emit(Resource.Success(result.data))
                }

                is Resource.Loading -> _coinDetailFlow.emit(Resource.Loading)
                is Resource.Error -> _coinDetailFlow.emit(Resource.Error(result.throwable))
            }
        }
    }

    fun currentPriceById(period: Duration) = viewModelScope.launch {
        savedStateHandle.get<String>(COIN_ID)?.let {
            currentPriceByIdUseCase.invoke(period, it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _currentPriceFlow.emit(result.data)
                    is Resource.Error -> {}
                }
            }
        }
    }

    fun addToFavourites() = viewModelScope.launch {
        coinDetailUI?.let {
            addToFavouritesUseCase.invoke(it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _addToFavourite.emit(
                        UIScreen.SnackBar(
                            stringResourceProvider.getString(R.string.add_to_favourites_successful)
                        )
                    )

                    is Resource.Error -> _addToFavourite.emit(
                        UIScreen.SnackBar(
                            result.throwable.message.toString()
                        )
                    )
                }
            }
        } ?: run {
            _addToFavourite.emit(
                UIScreen.SnackBar(stringResourceProvider.getString(R.string.something_went_wrong))
            )
        }
    }




}