package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Product
import com.example.model.ShopData
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralTextMuted
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralTextSecondary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.ui.theme.RokaPinkLight
import com.example.util.WhatsAppHelper

@Composable
fun ProductsSection(
  products: List<Product>,
  searchQuery: String,
  selectedCategoryId: String?,
  favoriteIds: Set<String>,
  onSelectCategory: (String?) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onProductClick: (Product) -> Unit,
  onResetFilters: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("products_section"),
  ) {
    // Title: NOS PRODUITS POPULAIRES
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = RokaBlue,
              fontWeight = FontWeight.Black,
              fontSize = 20.sp,
              letterSpacing = 1.sp,
            )
          ) {
            append("NOS ")
          }
          withStyle(
            style = SpanStyle(
              color = RokaPink,
              fontWeight = FontWeight.Black,
              fontSize = 20.sp,
              letterSpacing = 1.sp,
            )
          ) {
            append("PRODUITS POPULAIRES")
          }
        },
        modifier = Modifier.testTag("products_section_title"),
      )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = "Sélection officielle garantie 12 mois • Livraison 58 Wilayas",
      fontSize = 12.sp,
      color = NeutralTextSecondary,
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Filter Chips row (Tous, Cuisinières, Réfrigérateurs, etc.)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      FilterChip(
        selected = selectedCategoryId == null,
        onClick = { onSelectCategory(null) },
        label = { Text("Tous (${ShopData.products.size})", fontSize = 12.sp) },
        colors = FilterChipDefaults.filterChipColors(
          selectedContainerColor = RokaBlue,
          selectedLabelColor = NeutralWhite,
        ),
      )

      ShopData.categories.forEach { category ->
        val count = ShopData.products.count { it.categoryId == category.id }
        FilterChip(
          selected = selectedCategoryId == category.id,
          onClick = { onSelectCategory(category.id) },
          label = { Text("${category.emoji} ${category.name} ($count)", fontSize = 12.sp) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = RokaPink,
            selectedLabelColor = NeutralWhite,
          ),
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Results count & active search indication
    if (searchQuery.isNotEmpty() || selectedCategoryId != null) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(10.dp))
          .background(Color(0xFFF1F5F9))
          .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = "${products.size} produit(s) trouvé(s)",
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = RokaBlue,
        )

        Row(
          modifier = Modifier
            .clickable { onResetFilters() }
            .padding(4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          Icon(
            imageVector = Icons.Default.RestartAlt,
            contentDescription = "Réinitialiser",
            tint = RokaPink,
            modifier = Modifier.size(16.dp),
          )
          Text(
            text = "Réinitialiser",
            fontSize = 11.sp,
            color = RokaPink,
            fontWeight = FontWeight.Bold,
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
    }

    // Products List (1 column on mobile as requested)
    if (products.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(NeutralWhite)
          .border(1.dp, NeutralBorder, RoundedCornerShape(16.dp))
          .padding(32.dp),
        contentAlignment = Alignment.Center,
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text(text = "🔍", fontSize = 36.sp)
          Text(
            text = "Aucun produit trouvé",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = NeutralTextPrimary,
          )
          Text(
            text = "Essayez un autre mot-clé ou réinitialisez les filtres.",
            fontSize = 12.sp,
            color = NeutralTextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Button(
            onClick = onResetFilters,
            colors = ButtonDefaults.buttonColors(containerColor = RokaBlue),
            shape = RoundedCornerShape(20.dp),
          ) {
            Text("Voir tous les produits", color = NeutralWhite, fontSize = 12.sp)
          }
        }
      }
    } else {
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        products.forEach { product ->
          ProductCard(
            product = product,
            isFavorite = favoriteIds.contains(product.id),
            onToggleFavorite = { onToggleFavorite(product.id) },
            onClick = { onProductClick(product) },
            onOrderWhatsApp = {
              val msg = WhatsAppHelper.buildOrderMessage(product.name)
              WhatsAppHelper.openWhatsApp(context, msg)
            },
          )
        }
      }
    }
  }
}

@Composable
fun ProductCard(
  product: Product,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onClick: () -> Unit,
  onOrderWhatsApp: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(20.dp))
      .border(1.dp, NeutralBorder, RoundedCornerShape(20.dp))
      .clickable { onClick() }
      .testTag("product_card_${product.id}"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = NeutralWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
    ) {
      // Image Container with object-fit: contain (ContentScale.Fit)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(210.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(Color(0xFFF8FAFC)),
        contentAlignment = Alignment.Center,
      ) {
        Image(
          painter = painterResource(id = product.imageRes),
          contentDescription = "${product.brand} ${product.name}",
          // Strictly object-fit: contain as requested by prompt!
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(8.dp),
        )

        // Top left: Brand badge
        Box(
          modifier = Modifier
            .align(Alignment.TopStart)
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(RokaBlue)
            .padding(horizontal = 9.dp, vertical = 4.dp),
        ) {
          Text(
            text = product.brand,
            color = NeutralWhite,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp,
          )
        }

        // Top right: Favorite heart button
        IconButton(
          onClick = onToggleFavorite,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(6.dp)
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xDDFFFFFF)),
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favori",
            tint = if (isFavorite) RokaPink else NeutralTextMuted,
            modifier = Modifier.size(20.dp),
          )
        }

        // Bottom left: Garantie 12 mois badge
        Box(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(10.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xEEFFFFFF))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(6.dp))
            .padding(horizontal = 7.dp, vertical = 3.dp),
        ) {
          Text(
            text = "🛡️ Garantie 12 mois",
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = NeutralTextPrimary,
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Category tag & In Stock
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = product.categoryName.uppercase(),
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          color = RokaPink,
          letterSpacing = 0.5.sp,
        )

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF16A34A),
            modifier = Modifier.size(12.dp),
          )
          Text(
            text = "En stock",
            fontSize = 10.sp,
            color = Color(0xFF16A34A),
            fontWeight = FontWeight.SemiBold,
          )
        }
      }

      Spacer(modifier = Modifier.height(4.dp))

      // Product Name
      Text(
        text = product.name,
        color = NeutralTextPrimary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        lineHeight = 22.sp,
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Price: « Prix sur demande » (prompt instruction: Ne pas afficher de prix fictifs : utiliser « Prix sur demande »)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF1F5F9))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
          Text(
            text = product.priceTag,
            color = RokaPink,
            fontSize = 13.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.2.sp,
          )
        }

        // Details hint
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(3.dp),
          modifier = Modifier.clickable { onClick() },
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Détails",
            tint = RokaBlue,
            modifier = Modifier.size(14.dp),
          )
          Text(
            text = "Voir fiche",
            fontSize = 11.sp,
            color = RokaBlue,
            fontWeight = FontWeight.SemiBold,
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Blue Button: 💬 Commander sur WhatsApp
      Button(
        onClick = onOrderWhatsApp,
        colors = ButtonDefaults.buttonColors(
          containerColor = RokaBlue,
          contentColor = NeutralWhite,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("whatsapp_order_btn_${product.id}"),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_whatsapp),
            contentDescription = "WhatsApp",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp),
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Commander sur WhatsApp",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = NeutralWhite,
          )
        }
      }
    }
  }
}
