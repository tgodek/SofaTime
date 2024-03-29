package com.mangatalabs.tvshow_data.remote.dto.imagesResponse

import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    val backdrops: List<BackdropDto> = emptyList(),
    val id: Int? = null,
    val posters: List<PosterDto> = emptyList()
)