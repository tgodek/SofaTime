package com.mangatalabs.tvshow_presentation.tvShowHome.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder
import com.mangatalabs.core_ui.components.DefaultAlertDialog
import com.mangatalabs.tvshow_presentation.tvShowHome.components.SearchTvShowCard
import com.mangatalabs.tvshow_presentation.tvShowHome.components.TrendingSection
import com.mangatalabs.tvshow_presentation.tvShowHome.components.WorkInProgressCard
import com.mangatalabs.tvshow_presentation.tvShowHome.events.TvShowHomeEvents
import com.mangatalabs.tvshow_presentation.tvShowHome.viewModel.TvShowHomeViewModel

@Composable
fun TvShowHomeScreen(
    viewModel: TvShowHomeViewModel,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    onSearchCardClick: () -> Unit,
) {
    val isLoading = viewModel.isLoading

    if (viewModel.showAlert) {
        DefaultAlertDialog(
            title = "Seasons & Episodes",
            description = "We are working on the Episodes & Seasons tracking feature. It will be available in the next major version of the app!",
            confirmButtonText = "Dismiss",
            onDismissRequest = { viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog) },
            onConfirmButtonClick = { viewModel.onEvent(TvShowHomeEvents.DismissAlertDialog) },
            modifier = Modifier.clip(MaterialTheme.shapes.small)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TrendingSection(
                    items = viewModel.tvShowsState,
                    imageLoader = imageLoader,
                    onTvShowClick = onTvShowClick,
                    modifier = Modifier.placeholder(
                        visible = isLoading,
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium,
                        highlight = PlaceholderHighlight.fade()
                    )
                )
            }
            item {
                SearchTvShowCard(
                    modifier = Modifier
                        .placeholder(
                            visible = isLoading,
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    onSearchCardClick = onSearchCardClick
                )
            }
            item {
                WorkInProgressCard(
                    modifier = Modifier.placeholder(
                        visible = isLoading,
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium,
                        highlight = PlaceholderHighlight.fade()
                    ),
                    onInfoClick = { viewModel.onEvent(TvShowHomeEvents.ShowAlertDialog) })
            }
        }
    }
}
