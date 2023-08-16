package com.rawg.domain.usecase

import com.rawg.core.Resource
import com.rawg.data.repository.GameRepository
import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail
import javax.inject.Inject

internal class GetGameDetailUseCaseImpl @Inject constructor(
    private val repository: GameRepository,
) : GetGameDetailUseCase {
    override suspend fun invoke(param: ParamGameDetail): Resource<Game> =
        repository.getGameDetail(param)
}