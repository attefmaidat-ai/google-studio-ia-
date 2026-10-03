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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralTextPrimary
import com.example.ui.theme.NeutralTextSecondary
import com.example.ui.theme.NeutralWhite
import com.example.ui.theme.RokaBlue
import com.example.ui.theme.RokaPink
import com.example.util.WhatsAppHelper

data class Wilaya(val code: Int, val name: String, val delay: String)

private val algerianWilayas = listOf(
  Wilaya(1, "Adrar", "48h - 72h"),
  Wilaya(2, "Chlef", "24h - 48h"),
  Wilaya(3, "Laghouat", "24h - 48h"),
  Wilaya(4, "Oum El Bouaghi", "24h - 48h"),
  Wilaya(5, "Batna", "24h - 48h"),
  Wilaya(6, "Béjaïa", "24h - 48h"),
  Wilaya(7, "Biskra", "24h - 48h"),
  Wilaya(8, "Béchar", "48h - 72h"),
  Wilaya(9, "Blida", "24h Express"),
  Wilaya(10, "Bouira", "24h - 48h"),
  Wilaya(11, "Tamanrasset", "48h - 72h"),
  Wilaya(12, "Tébessa", "24h - 48h"),
  Wilaya(13, "Tlemcen", "24h - 48h"),
  Wilaya(14, "Tiaret", "24h - 48h"),
  Wilaya(15, "Tizi Ouzou", "24h - 48h"),
  Wilaya(16, "Alger", "24h Express"),
  Wilaya(17, "Djelfa", "24h - 48h"),
  Wilaya(18, "Jijel", "24h - 48h"),
  Wilaya(19, "Sétif", "24h - 48h"),
  Wilaya(20, "Saïda", "24h - 48h"),
  Wilaya(21, "Skikda", "24h - 48h"),
  Wilaya(22, "Sidi Bel Abbès", "24h - 48h"),
  Wilaya(23, "Annaba", "24h - 48h"),
  Wilaya(24, "Guelma", "24h - 48h"),
  Wilaya(25, "Constantine", "24h - 48h"),
  Wilaya(26, "Médéa", "24h - 48h"),
  Wilaya(27, "Mostaganem", "24h - 48h"),
  Wilaya(28, "M'Sila", "24h - 48h"),
  Wilaya(29, "Mascara", "24h - 48h"),
  Wilaya(30, "Ouargla", "48h - 72h"),
  Wilaya(31, "Oran", "24h Express"),
  Wilaya(32, "El Bayadh", "48h - 72h"),
  Wilaya(33, "Illizi", "72h"),
  Wilaya(34, "Bordj Bou Arréridj", "24h - 48h"),
  Wilaya(35, "Boumerdès", "24h Express"),
  Wilaya(36, "El Tarf", "24h - 48h"),
  Wilaya(37, "Tindouf", "72h"),
  Wilaya(38, "Tissemsilt", "24h - 48h"),
  Wilaya(39, "El Oued", "48h - 72h"),
  Wilaya(40, "Khenchela", "24h - 48h"),
  Wilaya(41, "Souk Ahras", "24h - 48h"),
  Wilaya(42, "Tipaza", "24h Express"),
  Wilaya(43, "Mila", "24h - 48h"),
  Wilaya(44, "Aïn Defla", "24h - 48h"),
  Wilaya(45, "Naâma", "48h - 72h"),
  Wilaya(46, "Aïn Témouchent", "24h - 48h"),
  Wilaya(47, "Ghardaïa", "48h - 72h"),
  Wilaya(48, "Relizane", "24h - 48h"),
  Wilaya(49, "Timimoun", "72h"),
  Wilaya(50, "Bordj Badji Mokhtar", "72h"),
  Wilaya(51, "Ouled Djellal", "48h - 72h"),
  Wilaya(52, "Béni Abbès", "72h"),
  Wilaya(53, "In Salah", "72h"),
  Wilaya(54, "In Guezzam", "72h"),
  Wilaya(55, "Touggourt", "48h - 72h"),
  Wilaya(56, "Djanet", "72h"),
  Wilaya(57, "El M'Ghair", "48h - 72h"),
  Wilaya(58, "El Meniaa", "48h - 72h"),
)

@Composable
fun WilayaDeliveryDialog(
  onDismiss: () -> Unit,
) {
  val context = LocalContext.current
  var filter by remember { mutableStateOf("") }

  val filteredWilayas = remember(filter) {
    if (filter.isBlank()) algerianWilayas
    else algerianWilayas.filter {
      it.name.contains(filter, ignoreCase = true) || it.code.toString().contains(filter)
    }
  }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = NeutralWhite),
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Icon(
              imageVector = Icons.Default.LocalShipping,
              contentDescription = null,
              tint = RokaPink,
            )
            Text(
              text = "Livraison 58 Wilayas",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = RokaBlue,
            )
          }

          IconButton(onClick = onDismiss) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Fermer",
              tint = NeutralTextSecondary,
            )
          }
        }

        Text(
          text = "Expédition rapide à domicile ou stop-desk avec paiement à la livraison.",
          fontSize = 12.sp,
          color = NeutralTextSecondary,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          value = filter,
          onValueChange = { filter = it },
          placeholder = { Text("Rechercher votre wilaya...", fontSize = 12.sp) },
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = null,
              tint = RokaBlue,
              modifier = Modifier.size(18.dp),
            )
          },
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
          shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
          modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 280.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          items(filteredWilayas) { wilaya ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF8FAFC))
                .padding(horizontal = 12.dp, vertical = 8.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Box(
                  modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E8F0)),
                  contentAlignment = Alignment.Center,
                ) {
                  Text(
                    text = "${wilaya.code}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = RokaBlue,
                  )
                }
                Text(
                  text = wilaya.name,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = NeutralTextPrimary,
                )
              }

              Text(
                text = wilaya.delay,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = RokaPink,
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
          onClick = {
            onDismiss()
            WhatsAppHelper.openWhatsApp(
              context,
              "Bonjour Roka Electro, je souhaite connaître les modalités de livraison dans ma wilaya."
            )
          },
          colors = ButtonDefaults.buttonColors(containerColor = RokaBlue),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text("Demander un devis livraison WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
