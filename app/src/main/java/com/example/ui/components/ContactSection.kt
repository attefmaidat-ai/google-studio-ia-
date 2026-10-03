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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.util.WhatsAppHelper

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
  val context = LocalContext.current

  val contactGradient = Brush.linearGradient(
    colors = listOf(
      RokaPink,
      Color(0xFF8B258D),
      RokaBlue,
    )
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp)
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(24.dp))
      .clip(RoundedCornerShape(24.dp))
      .background(contactGradient)
      .padding(24.dp)
      .testTag("contact_section"),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // Question mark badge
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(Color(0x33FFFFFF)),
        contentAlignment = Alignment.Center,
      ) {
        Text(text = "💬", fontSize = 24.sp)
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Texte : Une question ?
      Text(
        text = "Une question ?",
        color = NeutralWhite,
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 0.5.sp,
      )

      Spacer(modifier = Modifier.height(8.dp))

      // « Contactez-nous directement sur WhatsApp pour demander le prix ou commander. »
      Text(
        text = "« Contactez-nous directement sur WhatsApp pour demander le prix ou commander. »",
        color = Color(0xFFFDE8F3),
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Bouton : 💬 CONTACTER ROKA ELECTRO
      Button(
        onClick = {
          WhatsAppHelper.openWhatsApp(context, WhatsAppHelper.buildInquiryMessage())
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = NeutralWhite,
          contentColor = RokaBlue,
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("contact_whatsapp_button"),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_whatsapp),
            contentDescription = "WhatsApp",
            tint = Color.Unspecified,
            modifier = Modifier.size(22.dp),
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "CONTACTER ROKA ELECTRO",
            fontWeight = FontWeight.Black,
            fontSize = 13.sp,
            letterSpacing = 0.5.sp,
            color = RokaBlue,
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Direct telephone button
      OutlinedButton(
        onClick = { WhatsAppHelper.callShop(context) },
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = NeutralWhite,
        ),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0x99FFFFFF)),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("contact_phone_button"),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Appeler",
            tint = NeutralWhite,
            modifier = Modifier.size(18.dp),
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Appeler : 0791708328",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = NeutralWhite,
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Key assurances
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        AssurancePill(text = "📦 58 Wilayas")
        AssurancePill(text = "🛡️ Garantie 12M")
        AssurancePill(text = "⚡ Réponse rapide")
      }
    }
  }
}

@Composable
private fun AssurancePill(text: String) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(20.dp))
      .background(Color(0x22FFFFFF))
      .padding(horizontal = 8.dp, vertical = 4.dp),
  ) {
    Text(
      text = text,
      color = NeutralWhite,
      fontSize = 11.sp,
      fontWeight = FontWeight.SemiBold,
    )
  }
}
