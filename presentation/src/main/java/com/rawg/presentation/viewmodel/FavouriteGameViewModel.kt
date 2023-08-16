package com.rawg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawg.core.Resource
import com.rawg.domain.usecase.GetFavouriteGamesUseCase
import com.rawg.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavouriteGameViewModel @Inject constructor(
    getFavouriteGamesUseCase: GetFavouriteGamesUseCase,
) : ViewModel() {
    val favouriteGamesState: StateFlow<Resource<List<Game>>> =
        getFavouriteGamesUseCase().map { Resource.Success(data = it) }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            Resource.Idle(),
        )
}