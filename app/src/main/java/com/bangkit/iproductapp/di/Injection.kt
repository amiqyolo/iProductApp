package com.bangkit.iproductapp.di

import com.bangkit.iproductapp.data.ProductRepository

object Injection {
    fun provideRepository(): ProductRepository {
        return ProductRepository.getInstance()
    }
}