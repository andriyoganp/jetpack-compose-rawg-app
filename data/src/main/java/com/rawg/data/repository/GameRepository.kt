package com.rawg.data.repository

import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail
import com.rawg.model.param.ParamGameList
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getGames(param: ParamGameList): Resource<List<Game>>
    suspend fun getGameDetail(param: ParamGameDetail): Resource<Game>
    fun getFavouriteGames(): Flow<List<Game>>
    fun checkIsGameFavourite(param: ParamGameDetail): Flow<Game>
    suspend fun addToFavouriteGame(param: ParamGameDetail): Long
    suspend fun deleteFromFavouriteGame(param: ParamGameDetail): Int
}