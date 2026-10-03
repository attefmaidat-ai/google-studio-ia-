package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlueDark
import com.example.ui.theme.RokaPink
import com.example.util.WhatsAppHelper

@Composable
fun FooterSection(modifier: Modifier = Modifier) {
  val context = LocalContext.current

  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(RokaBlueDark)
      .padding(horizontal = 20.dp, vertical = 28.dp)
      .testTag("footer_section"),
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // Brand Logo in footer
      Text(
        text = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = RokaPink,
              fontWeight = FontWeight.Black,
              fontSize = 20.sp,
              letterSpacing = 1.sp,
            )
          ) {
            append("ROKA ")
          }
          withStyle(
            style = SpanStyle(
              color = Color(0xFF60A5FA),
              fontWeight = FontWeight.Black,
              fontSize = 20.sp,
              letterSpacing = 1.sp,
            )
          ) {
            append("ELECTRO")
          }
        },
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "Votre magasin d'électroménager de référence en Algérie",
        color = Color(0xFF94A3B8),
        fontSize = 11.sp,
        textAlign = TextAlign.Center,
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Delivery & Reassurance row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        FooterAssuranceItem(icon = Icons.Default.LocalShipping, label = "58 Wilayas")
        FooterAssuranceItem(icon = Icons.Default.Security, label = "Garantie 12 Mois")
        FooterAssuranceItem(
          icon = Icons.Default.Phone,
          label = "0791708328",
          onClick = { WhatsAppHelper.callShop(context) },
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Divider(color = Color(0xFF1E293B), thickness = 1.dp)

      Spacer(modifier = Modifier.height(16.dp))

      // Required footer copyright text:
      // « © 2026 Roka Electro — Commandes WhatsApp : 0791708328 »
      Text(
        text = "© 2026 Roka Electro — Commandes WhatsApp : 0791708328",
        color = Color(0xFFCBD5E1),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable {
          WhatsAppHelper.openWhatsApp(context, WhatsAppHelper.buildInquiryMessage())
        },
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "Paiement sécurisé à la livraison • Service après-vente dédié",
        color = Color(0xFF64748B),
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
      )
    }
  }
}

@Composable
private fun FooterAssuranceItem(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  label: String,
  onClick: (() -> Unit)? = null,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    modifier = Modifier
      .clip(RoundedCornerShape(8.dp))
      .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
      .padding(horizontal = 6.dp, vertical = 4.dp),
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = RokaPink,
      modifier = Modifier.size(16.dp),
    )
    Text(
      text = label,
      color = Color(0xFFE2E8F0),
      fontSize = 11.sp,
      fontWeight = FontWeight.SemiBold,
    )
  }
}
