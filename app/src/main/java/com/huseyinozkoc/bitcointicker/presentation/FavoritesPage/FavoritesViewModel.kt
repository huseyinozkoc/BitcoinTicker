package com.huseyinozkoc.bitcointicker.presentation.FavoritesPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.common.UIScreen
import com.huseyinozkoc.bitcointicker.domain.model.Favorites
import com.huseyinozkoc.bitcointicker.domain.usecase.favorite.DeleteFromFavoritesUseCase
import com.huseyinozkoc.bitcointicker.domain.usecase.favorite.GetFavoritesUseCase
import com.huseyinozkoc.bitcointicker.domain.utils.StringResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavoritesUseCase,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _deleteFromFavouritesFlow = MutableSharedFlow<UIScreen>()
    val deleteFromFavouritesFlow = _deleteFromFavouritesFlow.asSharedFlow()

    private val _favouritesFlow = MutableStateFlow<Resource<List<Favorites>>>(Resource.Loading)
    val favouritesFlow = _favouritesFlow.asStateFlow()

    init {
        getFavourites()
    }

    private fun getFavourites() = viewModelScope.launch {
        getFavouritesUseCase.invoke().collect { result ->
            _favouritesFlow.emit(result)
        }
    }

    fun deleteFromFavourites(favouritesUI: Favorites) = viewModelScope.launch {
        deleteFromFavouritesUseCase.invoke(favouritesUI).collect { result ->
            when (result) {
                Resource.Loading -> {}
                is Resource.Success -> {
                    _deleteFromFavouritesFlow.emit(
                        UIScreen.SnackBar(stringResourceProvider.getString(R.string.delete_from_favourite))
                    )
                    getFavourites()
                }
                is Resource.Error -> _deleteFromFavouritesFlow.emit(
                    UIScreen.SnackBar(result.throwable.message.toString())
                )
            }
        }
    }


}