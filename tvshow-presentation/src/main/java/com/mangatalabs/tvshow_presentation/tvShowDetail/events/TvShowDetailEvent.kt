package com.mangatalabs.tvshow_presentation.tvShowDetail.events

sealed class TvShowDetailEvent {
    object AddedToWatchList : TvShowDetailEvent()
    object RemoveFromWatchlist : TvShowDetailEvent()
}