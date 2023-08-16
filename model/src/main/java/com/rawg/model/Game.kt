package com.rawg.model

data class Game(
    val backgroundImage: String,
    val description: String,
    val name: String,
    val rating: Double,
    val ratingTop: Int,
    val released: String,
    val slug: String,
) {
    companion object {
        val defaultValue = Game(
            backgroundImage = "",
            description = "",
            name = "",
            rating = 0.0,
            ratingTop = 0,
            released = "",
            slug = "",
        )
    }
}