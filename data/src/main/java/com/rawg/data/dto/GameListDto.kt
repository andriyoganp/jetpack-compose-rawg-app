package com.rawg.data.dto


import com.google.gson.annotations.SerializedName

data class GameListDto(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<GameDetailDto>? = null,
)