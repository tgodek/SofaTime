package com.mangatalabs.tvshow_presentation.tvShowDetail.components.similarTvSection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangatalabs.tvshow_domain.model.tvShowList.TvShow
import com.mangatalabs.tvshow_presentation.common.components.DefaultTvShowCard

@Composable
internal fun SimilarTvShowSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    tvShows: List<TvShow>,
    onTvDetailClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text ="Similar Tv Shows",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(start = 20.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
        ) {
            items(tvShows) { tvShow ->
                DefaultTvShowCard(
                    imageLoader = imageLoader,
                    tvShow = tvShow,
                    imageHeight = 150.dp,
                    imageWidth = 125.dp,
                    onTvDetailClick = onTvDetailClick
                )
            }
        }
    }
}