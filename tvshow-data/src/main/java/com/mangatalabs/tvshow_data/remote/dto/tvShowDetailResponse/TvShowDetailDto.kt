package com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse

import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.CreatedByDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.GenreDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.LastEpisodeToAirDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.NetworkDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.ProductionCompanyDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.ProductionCountryDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.SeasonDto
import com.mangatalabs.tvshow_data.remote.dto.tvShowDetailResponse.SpokenLanguageDto
import kotlinx.serialization.Serializable

@Serializable
internal data class TvShowDetailDto(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val created_by: List<CreatedByDto> = emptyList(),
    val episode_run_time: List<Int> = emptyList(),
    val first_air_date: String? = null,
    val genres: List<GenreDto> = emptyList(),
    val homepage: String? = null,
    val id: Int? = null,
    val in_production: Boolean? = null,
    val languages: List<String> = emptyList(),
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAirDto? = null,
    val name: String? = null,
    val networks: List<NetworkDto> = emptyList(),
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String> = emptyList(),
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompanyDto> = emptyList(),
    val production_countries: List<ProductionCountryDto> = emptyList(),
    val seasons: List<SeasonDto> = emptyList(),
    val spoken_languages: List<SpokenLanguageDto> = emptyList(),
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
)