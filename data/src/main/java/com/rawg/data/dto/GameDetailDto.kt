package com.rawg.data.dto

import com.google.gson.annotations.SerializedName
import com.rawg.model.Game

data class GameDetailDto(
    @SerializedName("background_image")
    val backgroundImage: String? = null,
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("description_raw")
    val descriptionRaw: String? = null,
    @SerializedName("developers")
    val developers: List<Developer>? = null,
    @SerializedName("game_series_count")
    val gameSeriesCount: Int? = 0,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("name_original")
    val nameOriginal: String? = null,
    @SerializedName("platforms")
    val platforms: List<Platform>? = null,
    @SerializedName("playtime")
    val playtime: Int? = null,
    @SerializedName("publishers")
    val publishers: List<Publisher>? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("rating_top")
    val ratingTop: Int? = null,
    @SerializedName("ratings_count")
    val ratingsCount: Int? = null,
    @SerializedName("released")
    val released: String? = null,
    @SerializedName("reviews_count")
    val reviewsCount: Int? = null,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int? = null,
    @SerializedName("tba")
    val tba: Boolean? = false,
    @SerializedName("updated")
    val updated: String? = null,
) {
    data class Developer(
        @SerializedName("games_count")
        val gamesCount: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image_background")
        val imageBackground: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("slug")
        val slug: String? = null,
    )

    data class Genre(
        @SerializedName("games_count")
        val gamesCount: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image_background")
        val imageBackground: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("slug")
        val slug: String? = null,
    )

    data class Platform(
        @SerializedName("platform")
        val platform: PlatformDetail? = null,
        @SerializedName("released_at")
        val releasedAt: String? = null,
    ) {
        data class PlatformDetail(
            @SerializedName("games_count")
            val gamesCount: Int? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("image_background")
            val imageBackground: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("slug")
            val slug: String? = null,
            @SerializedName("year_start")
            val yearStart: Int? = null,
        )
    }

    data class Publisher(
        @SerializedName("games_count")
        val gamesCount: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image_background")
        val imageBackground: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("slug")
        val slug: String? = null,
    )

    fun toModel() = Game(
        backgroundImage = backgroundImage.orEmpty(),
        description = descriptionRaw.orEmpty(),
        name = name.orEmpty(),
        rating = rating ?: 0.0,
        ratingTop = ratingTop ?: 0,
        released = released.orEmpty(),
        slug = slug.orEmpty()
    )
}