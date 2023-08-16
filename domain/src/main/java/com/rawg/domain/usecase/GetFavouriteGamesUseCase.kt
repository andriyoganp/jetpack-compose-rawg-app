package com.rawg.domain.usecase

import com.rawg.model.Game
import kotlinx.coroutines.flow.Flow

interface GetFavouriteGamesUseCase {
    operator fun invoke(): Flow<List<Game>>
}