package com.mangatalabs.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.mangatalabs.core_ui.util.shake
import com.mangatalabs.tvshow_domain.model.tvShowList.TvShow

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TrendingSection(
    items: List<TvShow>,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    if (items.isEmpty()) {
        ErrorPlaceholder(
            modifier = modifier
        )
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "Trending Now",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            HorizontalPager(
                pageCount = items.size,
                pageSpacing = 12.dp,
                state = pagerState
            ) { page ->
                TvShowCarouselCard(
                    tvShow = items[page],
                    imageLoader = imageLoader,
                    onTvShowClick = onTvShowClick,
                    modifier = modifier
                )
            }
            PagerIndicator(
                itemCount = items.size,
                pagerState = pagerState,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicator(
    itemCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@Composable
fun ErrorPlaceholder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(220.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp, 40.dp)
                .shake(),
            imageVector = Icons.Filled.WifiOff,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "No Internet Connection"
        )
        Text(
            text = "Unable to load Content",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = "Check your Internet Connection",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
