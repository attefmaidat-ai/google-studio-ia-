package com.example.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink

@Composable
fun HeroSection(
  onDiscoverClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val heroGradient = Brush.verticalGradient(
    colors = listOf(
      RokaPink,
      Color(0xFF7A228B),
      RokaBlue,
    )
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(heroGradient)
      .padding(horizontal = 20.dp, vertical = 28.dp)
      .testTag("hero_section"),
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // Top badge
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(30.dp))
          .background(Color(0x33FFFFFF))
          .border(1.dp, Color(0x66FFFFFF), RoundedCornerShape(30.dp))
          .padding(horizontal = 14.dp, vertical = 6.dp),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color(0xFFFFD700),
            modifier = Modifier.size(16.dp),
          )
          Text(
            text = "BOUTIQUE OFFICIELLE ALGÉRIE",
            color = NeutralWhite,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Text : BIENVENUE CHEZ
      Text(
        text = "BIENVENUE CHEZ",
        color = Color(0xFFFDE8F3),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 3.sp,
      )

      Spacer(modifier = Modifier.height(4.dp))

      // ROKA
      Text(
        text = "ROKA",
        color = NeutralWhite,
        fontSize = 42.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.SansSerif,
      )

      // ELECTRO
      Text(
        text = "ELECTRO",
        color = Color(0xFF90C2FF),
        fontSize = 38.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 3.sp,
        fontFamily = FontFamily.SansSerif,
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subtitle
      Text(
        text = "Votre destination électroménager au meilleur prix !",
        color = NeutralWhite,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        lineHeight = 20.sp,
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Button: DÉCOUVRIR NOS PRODUITS →
      Button(
        onClick = onDiscoverClick,
        colors = ButtonDefaults.buttonColors(
          containerColor = NeutralWhite,
          contentColor = RokaPink,
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        modifier = Modifier
          .shadow(8.dp, RoundedCornerShape(30.dp))
          .testTag("hero_cta_button"),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        ) {
          Text(
            text = "DÉCOUVRIR NOS PRODUITS",
            fontWeight = FontWeight.Black,
            fontSize = 13.sp,
            letterSpacing = 0.5.sp,
            color = RokaPink,
          )
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = RokaPink,
            modifier = Modifier.size(16.dp),
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Hero graphic showcase: Card with ROKA ELECTRO appliances visual
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x22FFFFFF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .border(1.5.dp, Color(0x44FFFFFF), RoundedCornerShape(20.dp)),
      ) {
        Column(
          modifier = Modifier.padding(10.dp),
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(190.dp)
              .clip(RoundedCornerShape(14.dp)),
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_hero_appliances),
              contentDescription = "Électroménager Roka Electro",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxWidth(),
            )

            // Overlay banner at the bottom of the image
            Box(
              modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xCC071F4E))
                .padding(vertical = 8.dp, horizontal = 12.dp),
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Text(
                  text = "🔥 ARRIVAGES ÉLECTRO 2026",
                  color = NeutralWhite,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = "QUALITÉ GARANTIE",
                  color = Color(0xFFFFD700),
                  fontSize = 10.sp,
                  fontWeight = FontWeight.ExtraBold,
                )
              }
            }
          }
        }
      }
    }
  }
}
