package com.example.ui.components

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Product
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralTextSecondary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.ui.theme.RokaPinkLight
import com.example.util.WhatsAppHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailModal(
  product: Product,
  isFavorite: Boolean,
  onDismiss: () -> Unit,
  onToggleFavorite: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  BackHandler {
    onDismiss()
  }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = NeutralWhite,
    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    modifier = modifier.testTag("product_detail_sheet"),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
      // Top row: Brand + Actions (Close, Favorite, Share)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(RokaBlue)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
          Text(
            text = product.brand,
            color = NeutralWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.5.sp,
          )
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          IconButton(onClick = onToggleFavorite) {
            Icon(
              imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
              contentDescription = "Favori",
              tint = if (isFavorite) RokaPink else NeutralTextSecondary,
            )
          }

          IconButton(
            onClick = {
              WhatsAppHelper.shareProduct(context, product.name, product.brand)
            }
          ) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "Partager",
              tint = RokaBlue,
            )
          }

          IconButton(onClick = onDismiss) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Fermer",
              tint = NeutralTextPrimary,
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Product Image (object-fit: contain)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp)
          .clip(RoundedCornerShape(20.dp))
          .background(Color(0xFFF8FAFC))
          .border(1.dp, NeutralBorder, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center,
      ) {
        Image(
          painter = painterResource(id = product.imageRes),
          contentDescription = "${product.brand} ${product.name}",
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(12.dp),
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Category & In Stock
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = product.categoryName.uppercase(),
          color = RokaPink,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp,
        )

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFDCFCE7))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
          ) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = null,
              tint = Color(0xFF15803D),
              modifier = Modifier.size(13.dp),
            )
            Text(
              text = "Disponible en stock",
              color = Color(0xFF15803D),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Product Title
      Text(
        text = product.name,
        color = NeutralTextPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 26.sp,
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Price: Prix sur demande
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .background(RokaPinkLight)
          .padding(horizontal = 14.dp, vertical = 8.dp),
      ) {
        Text(
          text = product.priceTag,
          color = RokaPink,
          fontSize = 16.sp,
          fontWeight = FontWeight.Black,
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Delivery & Warranty badges
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        DetailBadge(
          icon = "🛡️",
          title = "Garantie 12 Mois",
          subtitle = "SAV assuré",
          modifier = Modifier.weight(1f),
        )
        DetailBadge(
          icon = "🚚",
          title = "58 Wilayas",
          subtitle = "Paiement à la livraison",
          modifier = Modifier.weight(1f),
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Description
      Text(
        text = "Description",
        color = RokaBlue,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = product.description,
        color = NeutralTextSecondary,
        fontSize = 13.sp,
        lineHeight = 20.sp,
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Key Features / Specs
      Text(
        text = "Caractéristiques principales",
        color = RokaBlue,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.height(8.dp))

      product.keySpecs.forEach { spec ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
          verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = RokaBlue,
            modifier = Modifier
              .size(16.dp)
              .padding(top = 2.dp),
          )
          Text(
            text = spec,
            color = NeutralTextPrimary,
            fontSize = 12.sp,
            lineHeight = 18.sp,
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      Divider(color = NeutralBorder)

      Spacer(modifier = Modifier.height(16.dp))

      // CTA Buttons: WhatsApp Order + Phone Call
      Button(
        onClick = {
          val message = WhatsAppHelper.buildOrderMessage(product.name)
          WhatsAppHelper.openWhatsApp(context, message)
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = RokaBlue,
          contentColor = NeutralWhite,
        ),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("modal_order_whatsapp_button"),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_whatsapp),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(22.dp),
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "Commander sur WhatsApp",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = NeutralWhite,
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      OutlinedButton(
        onClick = { WhatsAppHelper.callShop(context) },
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, RokaBlue),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = null,
            tint = RokaBlue,
            modifier = Modifier.size(18.dp),
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Appeler : 0791708328",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = RokaBlue,
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun DetailBadge(
  icon: String,
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(Color(0xFFF1F5F9))
      .padding(10.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Text(text = icon, fontSize = 20.sp)
      Column {
        Text(
          text = title,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = NeutralTextPrimary,
        )
        Text(
          text = subtitle,
          fontSize = 10.sp,
          color = NeutralTextSecondary,
        )
      }
    }
  }
}
