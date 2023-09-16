package com.mangatalabs.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WorkInProgressCard(
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = Modifier
                .offset(x = -(20.dp), y = 15.dp)
                .size(size = 18.dp)
                .clip(shape = RoundedCornerShape(percent = 50))
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(2.dp)
                .align(Alignment.TopEnd)
                .clickable { onInfoClick() }
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = Icons.Filled.PriorityHigh,
                contentDescription = "Warning"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp, 60.dp),
                    imageVector = Icons.Outlined.Image,
                    tint = Color.DarkGray.copy(alpha = 0.5f),
                    contentDescription = "Placeholder Image"
                )
            }
            Column(
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Text(
                    text = "The Last of Us",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Season 1 - Episode 4",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(Color.Blue.copy(alpha = 0.2f))
                        .clip(RoundedCornerShape(percent = 30))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(3.dp)
                            .background(Color.Blue.copy(alpha = 0.2f))
                            .clip(RoundedCornerShape(percent = 30))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkInProgressCardPreview() {
    WorkInProgressCard(onInfoClick = {})
}