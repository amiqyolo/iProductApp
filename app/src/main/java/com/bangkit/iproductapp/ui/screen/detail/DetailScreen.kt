package com.bangkit.iproductapp.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bangkit.iproductapp.R
import com.bangkit.iproductapp.di.Injection
import com.bangkit.iproductapp.model.Product
import com.bangkit.iproductapp.ui.ViewModelFactory
import com.bangkit.iproductapp.ui.common.UiState
import com.bangkit.iproductapp.ui.component.NavigationAppBar
import com.bangkit.iproductapp.ui.component.RatingBar
import com.bangkit.iproductapp.ui.theme.IProductAppTheme

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {}
            UiState.Loading -> viewModel.getProductById(id)
            is UiState.Success -> {
                DetailContent(
                    product = uiState.data,
                    onBackClick = navigateBack,
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    product: Product,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        NavigationAppBar(
            title = product.title,
            onBackPressed = onBackClick,
        )
        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.overview),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = product.description,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )
        RatingBar(
            rating = product.rating,
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
            ) {
                Text(
                    text = stringResource(R.string.price),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
                Text(
                    text = stringResource(R.string.item_price, product.price.toString()),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier
                )
            }
            Button(
                onClick = {},
            ) {
                Text(text = stringResource(id = R.string.add_to_cart))
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailScreenPreview() {
    IProductAppTheme {
        DetailContent(
            Product(
                id = 2,
                title = "iPhone X",
                description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
                price = 899,
                brand = "Apple",
                category = "smartphones",
                rating = 4.44,
                thumbnail = "https://i.dummyjson.com/data/products/2/thumbnail.jpg",
            ),
            onBackClick = {},
        )
    }
}
