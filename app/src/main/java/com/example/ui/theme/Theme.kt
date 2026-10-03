package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
  darkColorScheme(
    primary = RokaPink,
    onPrimary = NeutralWhite,
    primaryContainer = RokaPinkDark,
    onPrimaryContainer = NeutralWhite,
    secondary = RokaBlueLight,
    onSecondary = NeutralWhite,
    secondaryContainer = RokaBlue,
    onSecondaryContainer = NeutralWhite,
    tertiary = WhatsAppGreen,
    background = RokaNavy,
    surface = RokaBlueDark,
    onBackground = NeutralWhite,
    onSurface = NeutralWhite,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = RokaPink,
    onPrimary = NeutralWhite,
    primaryContainer = RokaPinkLight,
    onPrimaryContainer = RokaPinkDark,
    secondary = RokaBlue,
    onSecondary = NeutralWhite,
    secondaryContainer = RokaBlueBg,
    onSecondaryContainer = RokaBlue,
    tertiary = WhatsAppGreen,
    background = NeutralBackground,
    surface = NeutralSurface,
    onBackground = NeutralTextPrimary,
    onSurface = NeutralTextPrimary,
    surfaceVariant = NeutralBackground,
    onSurfaceVariant = NeutralTextSecondary,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Keep brand colors intact
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
