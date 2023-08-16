package com.rawg.data.service

import com.rawg.data.dto.GameDetailDto
import com.rawg.data.dto.GameListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GameService {
    @GET("games")
    suspend fun getGames(@QueryMap query: Map<String, String>): Response<GameListDto>

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") slug: String): Response<GameDetailDto>
}