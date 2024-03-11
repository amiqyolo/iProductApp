package com.bangkit.iproductapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.iproductapp.data.ProductRepository
import com.bangkit.iproductapp.model.Product
import com.bangkit.iproductapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Product>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Product>>> get() = _uiState

    fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProducts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { products ->
                    _uiState.value =
                        if (products.isNotEmpty()) UiState.Success(products) else UiState.Success(
                            emptyList()
                        )
                }
        }
    }

}