package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CategoryItem
import com.example.model.ShopData
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralTextSecondary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.ui.theme.RokaPinkLight

@Composable
fun CategoriesSection(
  selectedCategoryId: String?,
  onSelectCategory: (String?) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("categories_section"),
  ) {
    // Title: NOS CATÉGORIES (with "CATÉGORIES" in pink)
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
          append("CATÉGORIES")
        }
      },
      modifier = Modifier.testTag("categories_title"),
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = "Découvrez notre gamme complète d'appareils de qualité",
      fontSize = 12.sp,
      color = NeutralTextSecondary,
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 2 columns grid on mobile as requested!
    val categories = ShopData.categories

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      CategoryCard(
        category = categories[0],
        isSelected = selectedCategoryId == categories[0].id,
        onClick = { onSelectCategory(categories[0].id) },
        modifier = Modifier.weight(1f),
      )
      CategoryCard(
        category = categories[1],
        isSelected = selectedCategoryId == categories[1].id,
        onClick = { onSelectCategory(categories[1].id) },
        modifier = Modifier.weight(1f),
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      CategoryCard(
        category = categories[2],
        isSelected = selectedCategoryId == categories[2].id,
        onClick = { onSelectCategory(categories[2].id) },
        modifier = Modifier.weight(1f),
      )
      CategoryCard(
        category = categories[3],
        isSelected = selectedCategoryId == categories[3].id,
        onClick = { onSelectCategory(categories[3].id) },
        modifier = Modifier.weight(1f),
      )
    }
  }
}

@Composable
private fun CategoryCard(
  category: CategoryItem,
  isSelected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val borderColor = if (isSelected) RokaPink else NeutralBorder
  val containerColor = if (isSelected) RokaPinkLight else NeutralWhite

  Card(
    modifier = modifier
      .clip(RoundedCornerShape(16.dp))
      .border(if (isSelected) 2.dp else 1.dp, borderColor, RoundedCornerShape(16.dp))
      .clickable { onClick() }
      .testTag("category_card_${category.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = containerColor),
    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(CircleShape)
          .background(if (isSelected) NeutralWhite else Color(0xFFF1F5F9)),
        contentAlignment = Alignment.Center,
      ) {
        Text(text = category.emoji, fontSize = 22.sp)
      }

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = category.name,
          color = if (isSelected) RokaPink else NeutralTextPrimary,
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
        Text(
          text = category.subtitle,
          color = NeutralTextSecondary,
          fontSize = 10.sp,
          maxLines = 1,
        )
      }
    }
  }
}
