package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaPink

@Composable
fun TopAnnouncementBar(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(RokaPink)
      .padding(vertical = 7.dp, horizontal = 12.dp)
      .testTag("top_announcement_bar"),
    contentAlignment = Alignment.Center,
  ) {
    Row(
      modifier = Modifier
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      AnnouncementBadge(icon = "🚚", text = "Livraison rapide (58 Wilayas)")
      DotSeparator()
      AnnouncementBadge(icon = "🛡️", text = "Garantie 12 mois")
      DotSeparator()
      AnnouncementBadge(icon = "🎧", text = "Service client 7j/7")
    }
  }
}

@Composable
private fun AnnouncementBadge(icon: String, text: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(5.dp),
  ) {
    Text(text = icon, fontSize = 13.sp)
    Text(
      text = text,
      color = NeutralWhite,
      fontSize = 12.sp,
      fontWeight = FontWeight.SemiBold,
      letterSpacing = 0.2.sp,
    )
  }
}

@Composable
private fun DotSeparator() {
  Text(
    text = "•",
    color = Color(0x99FFFFFF),
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
  )
}
