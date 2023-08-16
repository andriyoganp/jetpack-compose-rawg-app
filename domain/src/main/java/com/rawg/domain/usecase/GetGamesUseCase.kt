package com.rawg.domain.usecase

import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.model.param.ParamGameList

interface GetGamesUseCase {
    suspend operator fun invoke(param: ParamGameList) : Resource<List<Game>>
}