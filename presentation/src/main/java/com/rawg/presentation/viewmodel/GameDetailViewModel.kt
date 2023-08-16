package com.rawg.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawg.core.Resource
import com.rawg.domain.usecase.AddToFavouriteGameUseCase
import com.rawg.domain.usecase.CheckIsFavouriteGameUseCase
import com.rawg.domain.usecase.DeleteFromFavouriteUseCase
import com.rawg.domain.usecase.GetGameDetailUseCase
import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail
import com.rawg.presentation.navigation.slugArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameDetailUseCase: GetGameDetailUseCase,
    private val addToFavouriteGameUseCase: AddToFavouriteGameUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val checkIsFavouriteGameUseCase: CheckIsFavouriteGameUseCase,
) : ViewModel() {

    private val slug: String = checkNotNull(savedStateHandle[slugArgument])

    private val _gameDetailState: MutableStateFlow<Resource<Game>> =
        MutableStateFlow(Resource.Idle())
    val gameDetailState: StateFlow<Resource<Game>> = _gameDetailState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        Resource.Idle()
    )

    private val _isFavourite = MutableStateFlow(false)
    val isFavourite: StateFlow<Boolean> = _isFavourite.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false,
    )

    init {
        getGameDetail()
        getIsFavourite()
    }

    fun getGameDetail() = viewModelScope.launch {
        if (_gameDetailState.value is Resource.Loading) {
            return@launch
        }
        _gameDetailState.value = Resource.Loading()
        _gameDetailState.value = getGameDetailUseCase(
            param = ParamGameDetail(slug)
        )
    }

    fun getIsFavourite() = viewModelScope.launch {
        checkIsFavouriteGameUseCase(ParamGameDetail(slug)).collectLatest {
            _isFavourite.value = it.slug == slug
        }
    }

    fun addToFavourite() = viewModelScope.launch {
        val gameDetailState = _gameDetailState.value
        val game = if (gameDetailState is Resource.Success) {
            gameDetailState.data
        } else null
        addToFavouriteGameUseCase(ParamGameDetail(slug, game = game))
    }

    fun deleteFromFavourite() = viewModelScope.launch {
        deleteFromFavouriteUseCase(ParamGameDetail(slug))
    }
}