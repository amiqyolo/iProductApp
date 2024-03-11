package com.bangkit.iproductapp.data

import com.bangkit.iproductapp.model.FakeProductDataSource
import com.bangkit.iproductapp.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {

    private val products = mutableListOf<Product>()

    init {
        if (products.isEmpty()) {
            FakeProductDataSource.dummyProduct.forEach {
                products.add(it)
            }
        }
    }

    fun getAllProducts(): Flow<List<Product>> {
        return flowOf(products)
    }

    fun getProductById(id: Int): Product {
        return products.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ProductRepository? = null

        fun getInstance(): ProductRepository =
            INSTANCE ?: synchronized(this) {
                ProductRepository().apply {
                    INSTANCE = this
                }
            }
    }
}