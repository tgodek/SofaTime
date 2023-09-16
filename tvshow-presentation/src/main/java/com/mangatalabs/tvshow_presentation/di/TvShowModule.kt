package com.mangatalabs.tvshow_presentation.di

import com.mangatalabs.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel
import com.mangatalabs.tvshow_presentation.tvShowSearch.viewModel.TvShowSearchViewModel
import com.mangatalabs.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel
import com.mangatalabs.tvshow_presentation.tvShowTracked.viewModel.TvShowTrackedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowModule = module {
    viewModel { TvShowSearchViewModel(get()) }
    viewModel { TvShowHomeViewModel(get()) }
    viewModel { params -> TvShowDetailViewModel(get(), tvShowId = params.get()) }
    viewModel { TvShowTrackedViewModel(get()) }
}