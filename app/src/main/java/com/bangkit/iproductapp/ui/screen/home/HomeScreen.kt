package com.bangkit.iproductapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.iproductapp.di.Injection
import com.bangkit.iproductapp.model.Product
import com.bangkit.iproductapp.ui.ViewModelFactory
import com.bangkit.iproductapp.ui.common.UiState
import com.bangkit.iproductapp.ui.component.ProductListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {}
            UiState.Loading -> viewModel.getAllProducts()
            is UiState.Success -> {
                HomeContent(
                    products = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    products: List<Product>,
    modifier: Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(products) { product ->
            ProductListItem(
                id = product.id,
                title = product.title,
                thumbnail = product.thumbnail,
                brand = product.brand,
                price = product.price,
                modifier = Modifier.clickable {
                    navigateToDetail(product.id)
                },
                navigateToDetail = navigateToDetail,
            )
        }
    }
}
