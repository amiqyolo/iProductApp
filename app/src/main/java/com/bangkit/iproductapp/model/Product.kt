package com.bangkit.iproductapp.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rating: Double,
    val brand: String,
    val category: String,
    val thumbnail: String,
)
