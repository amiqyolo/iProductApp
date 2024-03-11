package com.bangkit.iproductapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.iproductapp.data.ProductRepository
import com.bangkit.iproductapp.ui.screen.detail.DetailViewModel
import com.bangkit.iproductapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } as T
    }
}