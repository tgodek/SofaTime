package com.mangatalabs.core_ui.components

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
    size: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    icon: ImageVector,
    iconColor: Color
) {
    Text(
        modifier = modifier,
        color = color,
        fontSize = size,
        fontWeight = fontWeight,
        text = buildAnnotatedString {
            append(text)
            append(" ")
            appendInlineContent(myId, "[star]")
        },
        inlineContent = inlineContent(icon, iconColor, size)
    )
}

private const val myId = "inlineContent"
private fun inlineContent(
    icon: ImageVector,
    iconColor: Color,
    size: TextUnit
) = mapOf(
    Pair(
        myId,
        InlineTextContent(
            Placeholder(
                width = size,
                height = size,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            Icon(
                imageVector = icon,
                tint = iconColor,
                contentDescription = null
            )
        }
    )
)