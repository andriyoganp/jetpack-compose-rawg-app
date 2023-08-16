package com.rawg.domain.usecase

import com.rawg.data.repository.GameRepository
import com.rawg.model.param.ParamGameList
import javax.inject.Inject

internal class GetGamesUseCaseImpl @Inject constructor(
    private val repository: GameRepository,
) : GetGamesUseCase {
    override suspend fun invoke(param: ParamGameList) = repository.getGames(param)
}