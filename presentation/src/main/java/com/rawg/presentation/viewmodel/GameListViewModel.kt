package com.rawg.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawg.core.Resource
import com.rawg.domain.usecase.GetGamesUseCase
import com.rawg.model.Game
import com.rawg.model.param.ParamGameList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
) : ViewModel() {
    private val _gameList: MutableStateFlow<List<Game>> =
        MutableStateFlow(listOf())

    val gameList: StateFlow<List<Game>> = _gameList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        listOf()
    )

    private val _gameListUiState: MutableStateFlow<Resource<List<Game>>> =
        MutableStateFlow(Resource.Idle())
    val gameListUiState: StateFlow<Resource<List<Game>>> = _gameListUiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Idle()
    )

    private val _isFinish = mutableStateOf(false)
    private val _currentPage = mutableStateOf(1)

    init {
        getGamesUseCase()
    }

    fun getGamesUseCase(nextPage: Boolean = false) = viewModelScope.launch {
        if (_gameListUiState.value is Resource.Loading) {
            return@launch
        }
        if (_isFinish.value) {
            return@launch
        }

        if (nextPage) {
            _currentPage.value++
        } else {
            _currentPage.value = 1
            _isFinish.value = false
        }

        _gameListUiState.value = Resource.Loading()
        val result = getGamesUseCase(
            param = ParamGameList(
                page = _currentPage.value
            )
        )
        _gameListUiState.value = result
        when (result) {
            is Resource.Success -> {
                val currentList = _gameList.value.toMutableList()
                currentList.addAll(result.data)
                _gameList.value = currentList
                if (result.data.isEmpty()) {
                    _isFinish.value = true
                }
            }

            else -> {}
        }
    }
}