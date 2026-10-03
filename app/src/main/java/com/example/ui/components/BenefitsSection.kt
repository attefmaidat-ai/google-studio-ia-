package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralTextSecondary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.ui.theme.WhatsAppGreen

data class BenefitItem(
  val emoji: String,
  val title: String,
  val subtitle: String,
  val accentColor: Color,
  val bgColor: Color,
)

@Composable
fun BenefitsSection(modifier: Modifier = Modifier) {
  val benefits = listOf(
    BenefitItem(
      emoji = "🚚",
      title = "LIVRAISON RAPIDE",
      subtitle = "Dans toutes les wilayas",
      accentColor = RokaBlue,
      bgColor = Color(0xFFEFF5FF),
    ),
    BenefitItem(
      emoji = "🛡️",
      title = "GARANTIE 12 MOIS",
      subtitle = "Sur nos produits",
      accentColor = RokaPink,
      bgColor = Color(0xFFFFF0F6),
    ),
    BenefitItem(
      emoji = "🎧",
      title = "SERVICE CLIENT 7J/7",
      subtitle = "À votre écoute",
      accentColor = Color(0xFF7A228B),
      bgColor = Color(0xFFF9F0FF),
    ),
    BenefitItem(
      emoji = "💬",
      title = "COMMANDE WHATSAPP",
      subtitle = "Réponse rapide",
      accentColor = WhatsAppGreen,
      bgColor = Color(0xFFE9F9EE),
    ),
  )

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 20.dp)
      .testTag("benefits_section"),
  ) {
    // 2x2 grid layout
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      BenefitCard(benefit = benefits[0], modifier = Modifier.weight(1f))
      BenefitCard(benefit = benefits[1], modifier = Modifier.weight(1f))
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      BenefitCard(benefit = benefits[2], modifier = Modifier.weight(1f))
      BenefitCard(benefit = benefits[3], modifier = Modifier.weight(1f))
    }
  }
}

@Composable
private fun BenefitCard(benefit: BenefitItem, modifier: Modifier = Modifier) {
  Card(
    modifier = modifier
      .border(1.dp, NeutralBorder, RoundedCornerShape(16.dp)),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = NeutralWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalAlignment = Alignment.Start,
    ) {
      Box(
        modifier = Modifier
          .size(42.dp)
          .clip(CircleShape)
          .background(benefit.bgColor),
        contentAlignment = Alignment.Center,
      ) {
        Text(text = benefit.emoji, fontSize = 20.sp)
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = benefit.title,
        color = NeutralTextPrimary,
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 0.3.sp,
      )

      Spacer(modifier = Modifier.height(2.dp))

      Text(
        text = benefit.subtitle,
        color = NeutralTextSecondary,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
      )
    }
  }
}
