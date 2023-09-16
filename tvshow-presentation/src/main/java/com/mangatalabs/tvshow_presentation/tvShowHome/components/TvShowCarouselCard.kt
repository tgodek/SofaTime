package com.mangatalabs.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangatalabs.core.extensions.round
import com.mangatalabs.core_ui.components.TextWithIcon
import com.mangatalabs.core_ui.theme.*
import com.mangatalabs.tvshow_domain.model.tvShowList.TvShow
import core_ui.R as CoreUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowCarouselCard(
    tvShow: TvShow,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = { onTvShowClick(tvShow.id) },
    ) {
        Box(
            modifier = Modifier.height(220.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.backdropPath)
                    .placeholder(CoreUI.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.78f),
                        text = tvShow.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    TextWithIcon(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(Color.DarkGray)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        color = Color.White,
                        text = tvShow.voteAverage.round(1),
                        icon = Icons.Filled.Star,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        size = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}