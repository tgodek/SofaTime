package com.mangatalabs.tvshow_presentation.tvShowHome.events

sealed class TvShowHomeEvents {
    object ShowAlertDialog : TvShowHomeEvents()
    object DismissAlertDialog : TvShowHomeEvents()
    object Refresh : TvShowHomeEvents()
}