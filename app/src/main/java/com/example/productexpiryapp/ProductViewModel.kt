package com.example.productexpiryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository

    val allProducts: LiveData<List<Product>>

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.allProducts
    }

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }
}
