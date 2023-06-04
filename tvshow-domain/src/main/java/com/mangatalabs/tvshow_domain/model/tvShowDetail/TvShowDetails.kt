package com.mangatalabs.tvshow_domain.model.tvShowDetail

import com.mangatalabs.tvshow_domain.model.tvShowList.TvShow
import java.util.*

data class TvShowDetails(
    val id: Int,
    val name: String,
    val genres: List<Genre>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val lastAiredDate: Date?,
    val firstAirDate: Date?,
    val seasons: List<Season>,
    val episodeRunTime: List<Int>,
    val homepage: String?,
    val inProduction: Boolean,
    val networks: List<Network>,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String,
)

fun TvShowDetails.toTvShow() : TvShow {
    return TvShow(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}

