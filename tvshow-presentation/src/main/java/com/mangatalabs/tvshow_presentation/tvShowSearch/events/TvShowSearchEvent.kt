package com.mangatalabs.tvshow_presentation.tvShowSearch.events

sealed class TvShowSearchEvent {
    data class OnSearchTextChanged(val text: String) : TvShowSearchEvent()
    object OnSearchFinished : TvShowSearchEvent()
}