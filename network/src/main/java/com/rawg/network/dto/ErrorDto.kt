package com.rawg.network.dto


import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("detail")
    val detail: String? = null,
    @SerializedName("error")
    val error: String? = null
)