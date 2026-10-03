package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralSurface
import com.example.ui.theme.NeutralTextMuted
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.ui.theme.WhatsAppGreen
import com.example.util.WhatsAppHelper

@Composable
fun HeaderBar(
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  onNavigateToProducts: () -> Unit,
  onNavigateToContact: () -> Unit,
  onOpenWilayas: () -> Unit,
  favoriteCount: Int,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current

  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 4.dp, shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
      .testTag("header_bar"),
    color = NeutralSurface,
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
      // Row 1: Logo + Quick Actions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        // Logo: ROKA (Rose) ELECTRO (Bleu)
        Text(
          text = buildAnnotatedString {
            withStyle(
              style = SpanStyle(
                color = RokaPink,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp,
                letterSpacing = 1.sp,
              )
            ) {
              append("ROKA ")
            }
            withStyle(
              style = SpanStyle(
                color = RokaBlue,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp,
                letterSpacing = 1.sp,
              )
            ) {
              append("ELECTRO")
            }
          },
          fontFamily = FontFamily.SansSerif,
          modifier = Modifier.testTag("header_logo"),
        )

        // Actions: 58 Wilayas badge & Favorites
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(Color(0xFFEBF2FE))
              .clickable { onOpenWilayas() }
              .padding(horizontal = 9.dp, vertical = 5.dp)
              .testTag("wilaya_button"),
            contentAlignment = Alignment.Center,
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
              Icon(
                imageVector = Icons.Default.LocalShipping,
                contentDescription = "Livraison 58 wilayas",
                tint = RokaBlue,
                modifier = Modifier.size(15.dp),
              )
              Text(
                text = "58 Wilayas",
                color = RokaBlue,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
              )
            }
          }

          if (favoriteCount > 0) {
            BadgedBox(
              badge = {
                Badge(
                  containerColor = RokaPink,
                  contentColor = NeutralWhite,
                ) {
                  Text(text = "$favoriteCount", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }
            ) {
              Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favoris",
                tint = RokaPink,
                modifier = Modifier
                  .size(24.dp)
                  .padding(2.dp),
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Row 2: Search Bar
      OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("search_bar_input"),
        placeholder = {
          Text(
            text = "Rechercher un produit, marque...",
            fontSize = 13.sp,
            color = NeutralTextMuted,
          )
        },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Rechercher",
            tint = RokaBlue,
            modifier = Modifier.size(20.dp),
          )
        },
        trailingIcon = {
          if (searchQuery.isNotEmpty()) {
            IconButton(onClick = { onSearchQueryChange("") }) {
              Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Effacer la recherche",
                tint = NeutralTextMuted,
                modifier = Modifier.size(18.dp),
              )
            }
          }
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = RokaBlue,
          unfocusedBorderColor = NeutralBorder,
          focusedContainerColor = Color(0xFFF9FAFB),
          unfocusedContainerColor = Color(0xFFF9FAFB),
        ),
        singleLine = true,
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Row 3: Navigation Menu Bar
      // Menu : Produits | Contact | 💬 WhatsApp
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        HeaderNavPill(
          label = "Produits",
          bgColor = Color(0xFFEBF1FC),
          textColor = RokaBlue,
          onClick = onNavigateToProducts,
          testTag = "nav_products",
        )

        HeaderNavPill(
          label = "Contact",
          bgColor = Color(0xFFFFEAF2),
          textColor = RokaPink,
          onClick = onNavigateToContact,
          testTag = "nav_contact",
        )

        HeaderNavPill(
          label = "💬 WhatsApp",
          bgColor = Color(0xFFE6F8ED),
          textColor = WhatsAppGreen,
          onClick = {
            WhatsAppHelper.openWhatsApp(context, WhatsAppHelper.buildInquiryMessage())
          },
          testTag = "nav_whatsapp",
        )
      }
    }
  }
}

@Composable
private fun HeaderNavPill(
  label: String,
  bgColor: Color,
  textColor: Color,
  onClick: () -> Unit,
  testTag: String,
) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(20.dp))
      .background(bgColor)
      .clickable { onClick() }
      .padding(horizontal = 14.dp, vertical = 6.dp)
      .testTag(testTag),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = label,
      color = textColor,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
    )
  }
}
