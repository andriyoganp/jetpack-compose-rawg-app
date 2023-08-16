package com.rawg.domain.usecase

import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail

interface GetGameDetailUseCase {
    suspend operator fun invoke(param: ParamGameDetail): Resource<Game>
}