package com.rawg.domain.usecase

import com.rawg.model.param.ParamGameDetail

interface DeleteFromFavouriteUseCase {
    suspend operator fun invoke(param: ParamGameDetail): Int
}