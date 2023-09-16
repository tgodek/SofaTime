package com.mangatalabs.tvshow_presentation.tvShowDetail.root

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangatalabs.core_ui.util.observeWithLifecycle
import com.mangatalabs.core_ui.components.ErrorMessage
import com.mangatalabs.core_ui.events.UICoreEvent
import com.mangatalabs.core_ui.util.linkToWebPage
import com.mangatalabs.tvshow_presentation.tvShowDetail.components.headingSection.TvDetailsHeadingSection
import com.mangatalabs.tvshow_presentation.tvShowDetail.components.mediaSection.MediaSection
import com.mangatalabs.tvshow_presentation.tvShowDetail.components.networkSection.NetworkSection
import com.mangatalabs.tvshow_presentation.tvShowDetail.components.similarTvSection.SimilarTvShowSection
import com.mangatalabs.tvshow_presentation.tvShowDetail.components.storySection.StorySection
import com.mangatalabs.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel
import core_ui.R as CoreUI

@Composable
fun TvShowDetailScreen(
    imageLoader: ImageLoader,
    viewModel: TvShowDetailViewModel,
    snackbarHostState: SnackbarHostState,
    onTvDetailClick: (Int) -> Unit
) {
    val tvShowState = viewModel.tvShowDetailState
    val isLoading = viewModel.isLoadingState
    val context = LocalContext.current

    viewModel.eventsFlow.observeWithLifecycle {
        when (it) {
            is UICoreEvent.SnackbarEvent -> snackbarHostState.showSnackbar(it.uiText)
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (tvShowState == null) {
        ErrorMessage()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShowState.backdropPath)
                        .placeholder(CoreUI.drawable.image_placeholder)
                        .crossfade(true)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )

            }
            item {
                TvDetailsHeadingSection(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    viewModel = viewModel
                )
            }
            item {
                if (tvShowState.networks.isNotEmpty()) {
                    NetworkSection(
                        imageLoader = imageLoader,
                        networks = tvShowState.networks
                    )
                }
            }
            item {
                MediaSection(
                    imageLoader = imageLoader,
                    video = viewModel.videoState,
                    posters = viewModel.posterState,
                    onPlayVideoClick = { videoUrl ->
                        linkToWebPage(context, videoUrl)
                    }
                )
            }
            item {
                StorySection(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    story = tvShowState.overview
                )
            }
            if (viewModel.similarTvShowState.isNotEmpty()) {
                item {
                    SimilarTvShowSection(
                        imageLoader = imageLoader,
                        tvShows = viewModel.similarTvShowState,
                        onTvDetailClick = onTvDetailClick
                    )
                }
            }
        }
    }
}


