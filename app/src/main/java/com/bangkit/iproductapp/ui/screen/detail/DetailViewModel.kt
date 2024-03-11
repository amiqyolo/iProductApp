package com.bangkit.iproductapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.iproductapp.data.ProductRepository
import com.bangkit.iproductapp.model.Product
import com.bangkit.iproductapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Product>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Product>> get() = _uiState

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val result = repository.getProductById(id)
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(result)
        }
    }
}