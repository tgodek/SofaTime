package com.mangatalabs.tvshow_presentation.tvShowDetail.components.headingSection

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangatalabs.core.extensions.round
import com.mangatalabs.core_ui.components.TextWithIcon
import com.mangatalabs.tvshow_presentation.tvShowDetail.events.TvShowDetailEvent
import com.mangatalabs.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel

@Composable
internal fun TvDetailsHeadingSection(
    modifier: Modifier = Modifier,
    viewModel: TvShowDetailViewModel
) {
    val model = viewModel.headerState
    val isAdded = viewModel.isAddedToWatchList

    val iconAddColor by animateColorAsState(
        targetValue = if (isAdded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        animationSpec = tween(200, 100)
    )

    val iconAddSize by animateDpAsState(
        targetValue = if (isAdded) 45.dp else 40.dp,
        animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessMediumLow)
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HeaderRow(headerModel = model) {
            Text(
                modifier = Modifier.fillMaxWidth(0.80f),
                style = MaterialTheme.typography.displayLarge,
                text = model.title
            )
            IconButton(
                modifier = Modifier.offset(x = ((-3).dp)),
                onClick = {
                    if (isAdded) viewModel.onEvent(TvShowDetailEvent.RemoveFromWatchlist)
                    else viewModel.onEvent(TvShowDetailEvent.AddedToWatchList)
                }
            ) {
                Icon(
                    tint = iconAddColor,
                    imageVector = if (isAdded) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                    modifier = Modifier.size(iconAddSize),
                    contentDescription = "Add to WatchList"
                )
            }
        }
        HeaderRow(headerModel = model) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    ) { append(it.displayDate()) }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    ) { append("  ●  ${it.displaySeasons()}") }
                },
                style = MaterialTheme.typography.bodyLarge
            )
            RatingItem(score = it.score.round(1))
        }
        if (model.genres.isNotEmpty()) {
            HeaderRow(headerModel = model) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = it.displayGenres()
                )
            }
        }
        HeaderRow(headerModel = model) {
            val inlineContent = mapOf(
                Pair("inlineIcon",
                    InlineTextContent(
                        Placeholder(
                            width = 16.sp,
                            height = 16.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                        )
                    ) {
                        Icon(
                            Icons.Outlined.Timer,
                            "Stopwatch",
                        )
                    }
                )
            )
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = buildAnnotatedString {
                    if (it.runTime != null) {
                        append("${it.runTime} min ")
                    } else {
                        append(" - ")
                    }
                    appendInlineContent("inlineIcon", "[icon]")
                },
                inlineContent = inlineContent
            )
        }
    }
}

@Composable
private fun HeaderRow(
    modifier: Modifier = Modifier,
    headerModel: TvDetailsHeadingModel,
    rowElement: @Composable (TvDetailsHeadingModel) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        rowElement(headerModel)
    }
}

@Composable
private fun RatingItem(
    score: String,
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(vertical = 5.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        TextWithIcon(
            text = score,
            icon = Icons.Filled.Star,
            iconColor = MaterialTheme.colorScheme.secondary,
            size = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}