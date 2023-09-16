package com.mangatalabs.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import core_ui.R

val quickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold),
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = quickSand),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = quickSand),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = quickSand),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = quickSand),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = quickSand),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = quickSand),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = quickSand),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = quickSand),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = quickSand),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = quickSand),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = quickSand),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = quickSand),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = quickSand),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = quickSand),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = quickSand)
)