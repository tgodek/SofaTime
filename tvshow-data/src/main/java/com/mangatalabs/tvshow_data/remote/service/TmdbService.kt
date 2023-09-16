package com.mangatalabs.tvshow_data.remote.service

import com.mangatalabs.tvshow_data.remote.dto.imagesResponse.ImagesDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.TvShowDetailDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowResponse.TvShowResponseDto
import com.mangatalabs.tvshow_data.remote.dto.videoResponse.PagedTrailerDto

internal interface TmdbService {
    suspend fun getPopularTvShows(pageNumber: Int) : TvShowResponseDto
    suspend fun getTvShowDetails(id: Int) : TvShowDetailDto
    suspend fun getVideoForTvShow(id: Int) : PagedTrailerDto
    suspend fun getImagesForTvShow(id: Int) : ImagesDto
    suspend fun searchTvShows(query: String, pageNumber: Int) : TvShowResponseDto
    suspend fun getTrendingTvShows() : TvShowResponseDto
    suspend fun getSimilarTvShows(id: Int) : TvShowResponseDto
}