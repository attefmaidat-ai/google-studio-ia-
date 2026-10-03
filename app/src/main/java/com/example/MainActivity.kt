package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.BenefitsSection
import com.example.ui.components.CategoriesSection
import com.example.ui.components.ContactSection
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.FooterSection
import com.example.ui.components.HeaderBar
import com.example.ui.components.HeroSection
import com.example.ui.components.ProductDetailModal
import com.example.ui.components.ProductsSection
import com.example.ui.components.TopAnnouncementBar
import com.example.ui.components.WilayaDeliveryDialog
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NeutralBackground
import com.example.viewmodel.RokaViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        RokaElectroApp()
      }
    }
  }
}

@Composable
fun RokaElectroApp(
  viewModel: RokaViewModel = viewModel(),
) {
  val searchQuery by viewModel.searchQuery.collectAsState()
  val selectedCategoryId by viewModel.selectedCategoryId.collectAsState()
  val favoriteIds by viewModel.favoriteIds.collectAsState()
  val selectedProduct by viewModel.selectedProduct.collectAsState()
  val showWilayaDialog by viewModel.showWilayaDialog.collectAsState()

  val filteredProducts = viewModel.getFilteredProducts()
  val scrollState = rememberScrollState()
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .windowInsetsPadding(WindowInsets.statusBars),
    topBar = {
      Column(modifier = Modifier.fillMaxWidth()) {
        TopAnnouncementBar()
        HeaderBar(
          searchQuery = searchQuery,
          onSearchQueryChange = { viewModel.setSearchQuery(it) },
          onNavigateToProducts = {
            coroutineScope.launch {
              // Scroll down to products
              scrollState.animateScrollTo(800)
            }
          },
          onNavigateToContact = {
            coroutineScope.launch {
              // Scroll to contact section
              scrollState.animateScrollTo(scrollState.maxValue)
            }
          },
          onOpenWilayas = { viewModel.setShowWilayaDialog(true) },
          favoriteCount = favoriteIds.size,
        )
      }
    },
    floatingActionButton = {
      FloatingWhatsAppButton(
        modifier = Modifier
          .navigationBarsPadding()
          .padding(end = 6.dp, bottom = 6.dp),
      )
    },
    containerColor = NeutralBackground,
  ) { innerPadding ->
    BoxWithConstraints(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      contentAlignment = Alignment.TopCenter,
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 680.dp)
          .verticalScroll(scrollState),
      ) {
        // Hero Section
        HeroSection(
          onDiscoverClick = {
            coroutineScope.launch {
              scrollState.animateScrollTo(850)
            }
          },
        )

        // Benefits Section (4 blocs)
        BenefitsSection()

        // Categories Section (4 cartes en 2 colonnes)
        CategoriesSection(
          selectedCategoryId = selectedCategoryId,
          onSelectCategory = { catId ->
            viewModel.selectCategory(catId)
            coroutineScope.launch {
              scrollState.animateScrollTo(1000)
            }
          },
        )

        // Popular Products Section (grille/liste 1 colonne, object-fit contain, bouton bleu WhatsApp)
        ProductsSection(
          products = filteredProducts,
          searchQuery = searchQuery,
          selectedCategoryId = selectedCategoryId,
          favoriteIds = favoriteIds,
          onSelectCategory = { viewModel.selectCategory(it) },
          onToggleFavorite = { viewModel.toggleFavorite(it) },
          onProductClick = { viewModel.selectProduct(it) },
          onResetFilters = { viewModel.clearFilters() },
        )

        // Contact Section (Rose/Bleu gradient, "Une question ?", bouton WhatsApp + appel)
        ContactSection()

        // Footer Section (Fond bleu foncé, © 2026 Roka Electro — Commandes WhatsApp : 0791708328)
        FooterSection()

        // Bottom spacing for system navigation bar & floating WhatsApp FAB
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(bottom = 72.dp)
        )
      }
    }

    // Product Detail BottomSheet
    selectedProduct?.let { product ->
      ProductDetailModal(
        product = product,
        isFavorite = favoriteIds.contains(product.id),
        onDismiss = { viewModel.selectProduct(null) },
        onToggleFavorite = { viewModel.toggleFavorite(product.id) },
      )
    }

    // 58 Wilayas Delivery Calculator Modal
    if (showWilayaDialog) {
      WilayaDeliveryDialog(
        onDismiss = { viewModel.setShowWilayaDialog(false) },
      )
    }
  }
}
