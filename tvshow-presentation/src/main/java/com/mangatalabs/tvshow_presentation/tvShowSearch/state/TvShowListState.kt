package com.mangatalabs.tvshow_presentation.tvShowSearch.state

import com.mangatalabs.tvshow_domain.model.tvShowList.TvShow

data class TvShowListState(
    val tvShows: List<TvShow> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 1,
)