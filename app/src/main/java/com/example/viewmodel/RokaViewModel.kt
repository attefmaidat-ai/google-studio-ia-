package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.Product
import com.example.model.ShopData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RokaViewModel : ViewModel() {
  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  private val _selectedCategoryId = MutableStateFlow<String?>(null)
  val selectedCategoryId: StateFlow<String?> = _selectedCategoryId.asStateFlow()

  private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())
  val favoriteIds: StateFlow<Set<String>> = _favoriteIds.asStateFlow()

  private val _selectedProduct = MutableStateFlow<Product?>(null)
  val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

  private val _showWilayaDialog = MutableStateFlow(false)
  val showWilayaDialog: StateFlow<Boolean> = _showWilayaDialog.asStateFlow()

  fun setSearchQuery(query: String) {
    _searchQuery.value = query
  }

  fun selectCategory(categoryId: String?) {
    _selectedCategoryId.value = if (_selectedCategoryId.value == categoryId) null else categoryId
  }

  fun toggleFavorite(productId: String) {
    val current = _favoriteIds.value.toMutableSet()
    if (current.contains(productId)) {
      current.remove(productId)
    } else {
      current.add(productId)
    }
    _favoriteIds.value = current
  }

  fun selectProduct(product: Product?) {
    _selectedProduct.value = product
  }

  fun setShowWilayaDialog(show: Boolean) {
    _showWilayaDialog.value = show
  }

  fun clearFilters() {
    _searchQuery.value = ""
    _selectedCategoryId.value = null
  }

  fun getFilteredProducts(): List<Product> {
    val query = _searchQuery.value.trim().lowercase()
    val category = _selectedCategoryId.value

    return ShopData.products.filter { product ->
      val matchesCategory = category == null || product.categoryId == category
      val matchesQuery = query.isEmpty() ||
          product.name.lowercase().contains(query) ||
          product.brand.lowercase().contains(query) ||
          product.categoryName.lowercase().contains(query) ||
          product.description.lowercase().contains(query)

      matchesCategory && matchesQuery
    }
  }
}
