package com.mangatalabs.tvshow_presentation.tvShowDetail.components.networkSection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangatalabs.tvshow_domain.model.tvShowDetail.Network

@Composable
internal fun NetworkSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    networks: List<Network>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
    ) {
        items(networks) { network ->
            NetworkChip(imageLoader, url = network.logoPath)
        }
    }
}

@Composable
private fun NetworkChip(imageLoader: ImageLoader, url: String?) {
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = MaterialTheme.shapes.medium)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 90.dp, height = 45.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .align(Alignment.Center),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}