package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object WhatsAppHelper {
  const val PHONE_NUMBER_DISPLAY = "0791708328"
  const val PHONE_NUMBER_INTL = "213791708328"

  fun buildOrderMessage(productName: String): String {
    return "Bonjour Roka Electro, je souhaite commander : $productName"
  }

  fun buildInquiryMessage(): String {
    return "Bonjour Roka Electro, je vous contacte pour demander un prix et des renseignements sur vos produits."
  }

  fun openWhatsApp(context: Context, message: String) {
    try {
      val encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
      val url = "https://wa.me/$PHONE_NUMBER_INTL?text=$encodedMessage"
      val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }

      // Check if WhatsApp is explicitly installed
      val pm = context.packageManager
      val whatsappIntent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        setPackage("com.whatsapp")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }

      if (whatsappIntent.resolveActivity(pm) != null) {
        context.startActivity(whatsappIntent)
      } else {
        context.startActivity(intent)
      }
    } catch (e: Exception) {
      Toast.makeText(
        context,
        "Impossible d'ouvrir WhatsApp. Contactez directement le $PHONE_NUMBER_DISPLAY",
        Toast.LENGTH_LONG,
      ).show()
    }
  }

  fun callShop(context: Context) {
    try {
      val dialIntent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$PHONE_NUMBER_DISPLAY")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }
      context.startActivity(dialIntent)
    } catch (e: Exception) {
      Toast.makeText(
        context,
        "Appelez directement le $PHONE_NUMBER_DISPLAY",
        Toast.LENGTH_SHORT,
      ).show()
    }
  }

  fun shareProduct(context: Context, productName: String, brand: String) {
    try {
      val shareText = "Découvrez chez ROKA ELECTRO Algérie : $brand — $productName. Commandes WhatsApp au $PHONE_NUMBER_DISPLAY !"
      val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
      }
      val shareIntent = Intent.createChooser(sendIntent, "Partager ce produit")
      shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      context.startActivity(shareIntent)
    } catch (e: Exception) {
      // Ignored
    }
  }
}
