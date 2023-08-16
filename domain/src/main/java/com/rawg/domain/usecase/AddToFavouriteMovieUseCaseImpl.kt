package com.rawg.domain.usecase

import com.rawg.data.repository.GameRepository
import com.rawg.model.param.ParamGameDetail
import javax.inject.Inject

internal class AddToFavouriteGameUseCaseImpl @Inject constructor(
    private val repository: GameRepository,
) : AddToFavouriteGameUseCase {
    override suspend fun invoke(param: ParamGameDetail) = repository.addToFavouriteGame(param)
}