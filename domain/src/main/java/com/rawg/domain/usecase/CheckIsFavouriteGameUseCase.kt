package com.rawg.domain.usecase

import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail
import kotlinx.coroutines.flow.Flow

interface CheckIsFavouriteGameUseCase {
    operator fun invoke(param: ParamGameDetail): Flow<Game>
}