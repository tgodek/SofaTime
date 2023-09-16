package com.mangatalabs.tvshow_presentation.tvShowSearch.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder
import com.mangatalabs.core_ui.components.DefaultSearchBar
import com.mangatalabs.core_ui.components.EmptyListMessage
import com.mangatalabs.core_ui.util.loadMore
import com.mangatalabs.tvshow_presentation.tvShowSearch.components.SearchTvShowCard
import com.mangatalabs.tvshow_presentation.tvShowSearch.events.TvShowSearchEvent
import com.mangatalabs.tvshow_presentation.tvShowSearch.viewModel.TvShowSearchViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
@Composable
fun TvShowSearchScreen(
    viewModel: TvShowSearchViewModel,
    imageLoader: ImageLoader,
    onTvDetailClick: (Int) -> Unit,
) {
    val state = viewModel.tvShowsState
    val searchText by viewModel.searchState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()
    val loadMore by remember { derivedStateOf { scrollState.loadMore() } }

    LaunchedEffect(loadMore && !state.isLoading && !state.endReached && state.error.isEmpty()) {
        snapshotFlow { loadMore }
            .filter { it }
            .collect {
                viewModel.loadNextTvShows()
            }
    }

    LaunchedEffect(Unit) {
        viewModel.searchState
            .drop(1)
            .debounce(500)
            .distinctUntilChanged()
            .collectLatest {
                viewModel.onEvent(TvShowSearchEvent.OnSearchFinished)
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DefaultSearchBar(
            text = searchText,
            placeholderText = "Search Tv Shows...",
            onTextChange = {
                viewModel.onEvent(TvShowSearchEvent.OnSearchTextChanged(it))
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = scrollState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.tvShows.isNotEmpty()) {
                items(state.tvShows) { tvShow ->
                    SearchTvShowCard(
                        tvShow = tvShow,
                        onTvDetailClick = onTvDetailClick,
                        imageLoader = imageLoader
                    )
                }
            }
            if (state.isLoading) {
                if (state.tvShows.isEmpty()) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            repeat(5) {
                                CardPlaceholder()
                            }
                        }
                    }
                } else {
                    item {
                        Row {
                            CircularProgressIndicator(
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }
                }
            }
            if (state.tvShows.isEmpty() && !state.isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyListMessage(message = "Couldn't find any Tv Show")
                    }
                }
            }
        }
    }
}

@Composable
private fun CardPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .placeholder(
                visible = true,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium,
                highlight = PlaceholderHighlight.fade()
            )
    )
}