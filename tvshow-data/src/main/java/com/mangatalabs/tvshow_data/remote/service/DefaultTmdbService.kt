package com.mangatalabs.tvshow_data.remote.service

import com.mangatalabs.core.util.AppConfiguration
import com.mangatalabs.tvshow_data.remote.dto.imagesResponse.ImagesDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.TvShowDetailDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowResponse.TvShowResponseDto
import com.mangatalabs.tvshow_data.remote.dto.videoResponse.PagedTrailerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class DefaultTmdbService(private val client: HttpClient, private val appConfiguration: AppConfiguration) :
    TmdbService {
    override suspend fun getPopularTvShows(pageNumber: Int): TvShowResponseDto {
        return client.get(Endpoint.popularTvShows()) {
            parameter("page", pageNumber)
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }

    override suspend fun getTvShowDetails(id: Int): TvShowDetailDto {
        return client.get(Endpoint.tvShowsDetails(id)) {
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }

    override suspend fun getVideoForTvShow(id: Int): PagedTrailerDto {
        return client.get(Endpoint.tvShowTrailer(id)) {
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }

    override suspend fun getImagesForTvShow(id: Int): ImagesDto {
        return client.get(Endpoint.tvShowImages(id)){
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }

    override suspend fun searchTvShows(query: String, pageNumber: Int): TvShowResponseDto {
        return client.get(Endpoint.searchTvShows()) {
            parameter("api_key", appConfiguration.TMDB_API_KEY)
            parameter("include_adult", false)
            parameter("query", query)
            parameter("page", pageNumber)
        }.body()
    }

    override suspend fun getTrendingTvShows(): TvShowResponseDto {
        return client.get(Endpoint.trendingTvShows()) {
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }

    override suspend fun getSimilarTvShows(id: Int): TvShowResponseDto {
        return client.get(Endpoint.similarTvShows(id)) {
            parameter("api_key", appConfiguration.TMDB_API_KEY)
        }.body()
    }
}