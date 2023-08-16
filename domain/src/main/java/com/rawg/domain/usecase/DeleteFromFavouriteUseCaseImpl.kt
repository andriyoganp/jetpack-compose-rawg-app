package com.rawg.domain.usecase

import com.rawg.data.repository.GameRepository
import com.rawg.model.param.ParamGameDetail
import javax.inject.Inject

internal class DeleteFromFavouriteUseCaseImpl @Inject constructor(
    private val repository: GameRepository,
) : DeleteFromFavouriteUseCase {
    override suspend fun invoke(param: ParamGameDetail) = repository.deleteFromFavouriteGame(param)
}