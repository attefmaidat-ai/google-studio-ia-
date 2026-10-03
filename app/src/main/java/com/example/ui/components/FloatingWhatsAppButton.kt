package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.WhatsAppGreen
import com.example.util.WhatsAppHelper

@Composable
fun FloatingWhatsAppButton(
  modifier: Modifier = Modifier,
  onCustomClick: (() -> Unit)? = null,
) {
  val context = LocalContext.current

  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val scale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.06f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse,
    ),
    label = "scale",
  )

  Box(
    modifier = modifier
      .scale(scale)
      .testTag("floating_whatsapp_btn"),
    contentAlignment = Alignment.Center,
  ) {
    FloatingActionButton(
      onClick = {
        if (onCustomClick != null) {
          onCustomClick()
        } else {
          WhatsAppHelper.openWhatsApp(context, WhatsAppHelper.buildInquiryMessage())
        }
      },
      containerColor = WhatsAppGreen,
      contentColor = NeutralWhite,
      shape = RoundedCornerShape(28.dp),
      elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          painter = painterResource(id = R.drawable.ic_whatsapp),
          contentDescription = "Contacter sur WhatsApp",
          tint = Color.Unspecified,
          modifier = Modifier.size(26.dp),
        )
        Text(
          text = "WhatsApp",
          color = NeutralWhite,
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
        )
      }
    }
  }
}
